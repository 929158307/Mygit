package cn.itcast.fore.web.action;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import cn.itcast.bos.utils.Md5Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.utils.MailUtils;
import cn.itcast.crm.service.Customer;
import cn.itcast.crm.service.CustomerService;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
@Results({
	@Result(name="login_success",type="redirect",location="/index.html"),
	@Result(name="login_fail",type="redirect",location="/login.html")
})
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer model = new Customer();
	public Customer getModel() {
		return model;
	}
	
	@Autowired
	private CustomerService customerPRoxy;
	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	
	/**
	  * @Description: 验证调用CRM查询手机号是否存在
	  * @return   true：验证通过   false:验证不通过
	 */
	@Action("customerAction_validateTel")
	public String validateTel() throws Exception {
		Boolean flag = customerPRoxy.validateTel(model.getTelephone());
		ServletActionContext.getResponse().getWriter().write(flag.toString());
		return NONE;
	}
	
	/**
	  * @Description: 给客户发送短信验证码
	  * @return
	  * @throws Exception
	  *	  
	 */
	@Action("customerAction_sendCheckcode")
	public String sendCheckcode() throws Exception {
		String checkcode = RandomStringUtils.randomNumeric(4);
		System.out.println("验证码："+checkcode);
		final Map<String, Object> map = new HashMap<>();
		map.put("code", checkcode);
		//调用第三方短信平台发送短信
		Boolean flag = true;
//		Boolean flag = AliSmsUtil.sendMessage(model.getTelephone(), "SMS_119087143", map);
		//利用消息队列发送短信
		jmsTemplate.send("sendMessage", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("tel",model.getTelephone());
				mapMessage.setString("model","SMS_119087143");
				mapMessage.setObject("map",map);
				return mapMessage;
			}
		});
		if(flag){
			//将正确验证码存Session中
			ServletActionContext.getRequest().getSession().setAttribute(model.getTelephone(), checkcode);
		}
		ServletActionContext.getResponse().getWriter().write(flag.toString());
		return NONE;
	}
	
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	  * @Description: 调用CRM注册客户信息
	  * @return 0：注册失败   1:注册成功    2：验证码不正确
	  * @throws Exception
	  *	  
	 */
	@Action("customerAction_regist")
	public String regist() throws Exception {
		try {
			//获取正确验证码
			String realChecode = (String) ServletActionContext.getRequest().getSession().getAttribute(model.getTelephone());
			if(StringUtils.isNotBlank(realChecode)){
				if(realChecode.equals(checkcode)){
					//调用CRM完成注册
					model.setPassword(Md5Util.encode(model.getPassword()));
					customerPRoxy.regist(model);
					//TODO 发送短信
					//生成激活码-将来手机号跟激活码一一对应
					String activeCode = UUID.randomUUID().toString()+UUID.randomUUID().toString();
					final String content =  "欢迎您注册速运快递，为了提供更好的服务，请您在24小时内激活账户!!</br>"
							+ "<a href='"+MailUtils.activeUrl+"?telephone="+model.getTelephone()+"&activeCode="+activeCode +"'>点击激活账户</a>";
					//TODO 给用户发送激活账户邮件
					//运用消息队列发送邮件功能
					jmsTemplate.send("sendMail", new MessageCreator() {
						@Override
						public Message createMessage(Session session) throws JMSException {
							MapMessage mapMessage = session.createMapMessage();
							mapMessage.setString("title","欢迎注册快递");
							mapMessage.setString("text",content);
							mapMessage.setString("email",model.getEmail());
							return mapMessage;
						}
					});
//					MailUtils.sendMail("欢迎注册快递", content, model.getEmail());
					//将激活码存储--redis内存数据库中
					redisTemplate.opsForValue().set(model.getTelephone(), activeCode, 24, TimeUnit.HOURS);
					//将Session中验证码移除
					ServletActionContext.getRequest().getSession().removeAttribute(model.getTelephone());
					ServletActionContext.getResponse().getWriter().write("1");
				}else{
					//验证码不正确
					ServletActionContext.getResponse().getWriter().write("2");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServletActionContext.getResponse().getWriter().write("0");
		}
		return NONE;
	}

	//激活链接中提交  url提交激活码
	private String activeCode;
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	/**
	  * @Description: 激活邮件-确保邮箱是客户
	 */
	@Action("customerAction_activeMail")
	public String activeMail() throws Exception {
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		//1、查询客户激活状态
		if(StringUtils.isNotBlank(model.getTelephone())&&StringUtils.isNotBlank(activeCode)){
			Customer customer = customerPRoxy.findByType(model.getTelephone());
			if(customer!=null){
				if(customer.getType()==null){
					//未激活状态
					//2、判断激活码是否正确
					String realActiveCode = redisTemplate.opsForValue().get(model.getTelephone());
					if(StringUtils.isNotBlank(realActiveCode)){
						if(activeCode.equals(realActiveCode)){
							//3、调用CRM激活账户
							customerPRoxy.activeAccount(customer.getId());
							//清除redis数据库中激活码
							redisTemplate.delete(model.getTelephone());
							ServletActionContext.getResponse().getWriter().write("激活码成功，祝您使用愉快！");
						}else{
							//激活码错误
							ServletActionContext.getResponse().getWriter().write("激活码错误");
						}
					}else{
						//激活码失效
						ServletActionContext.getResponse().getWriter().write("激活码失效");
					}
				}else{
					//账户已经激活
					ServletActionContext.getResponse().getWriter().write("账户已经激活");
				}
			}else{
				//手机号不存在
				ServletActionContext.getResponse().getWriter().write("手机号不存在");
			}
		}else{
			//激活码或者手机为空
			ServletActionContext.getResponse().getWriter().write("激活码或者手机为空");
		}
		return NONE;
	}
	
	/**
	  * @Description: 调用CRM实现登陆
	  * @return
	  * @throws Exception
	  *	  
	 */
	@Action("customerAction_login")
	public String login() throws Exception {
		//判断验证码
		if(StringUtils.isNotBlank(checkcode)){
			//获取正确验证码
			String realCheckcode = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
			if(checkcode.equals(realCheckcode)){
				//根据手机号，密码 调用CRM查询客户记录是否存在
				//注意：密码需要加密
				Customer customer = customerPRoxy.login(model.getTelephone(), Md5Util.encode(model.getPassword()));
				if(customer!=null){
					//将客户对象存Session中，调转首页
					ServletActionContext.getRequest().getSession().setAttribute("loginCustomer", customer);
					return "login_success";
				}
			}
		}
		return "login_fail";
	}
}
