package cn.itcast.fore.web.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.domain.take_delivery.Order;
import cn.itcast.bos.service.take_delivery.OrderService;
import cn.itcast.crm.service.Customer;


@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
@Results({
	@Result(name="order_success",type="redirect",location="/order_success.html")
//	@Result(name="login_fail",type="redirect",location="/login.html")
})
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order model = new Order();
	public Order getModel() {
		return model;
	}
	
	@Autowired
	private OrderService orderProxy;
	
	//接收表单中寄件人省市区 收件人省市区  形式：北京市/北京市/昌平区
	private String sendAreaInfo;
	private String recAreaInfo;
	public void setSendAreaInfo(String sendAreaInfo) {
		this.sendAreaInfo = sendAreaInfo;
	}
	public void setRecAreaInfo(String recAreaInfo) {
		this.recAreaInfo = recAreaInfo;
	}



	/**
	  * @Description: 1、封装订单对象中属性 2、远程调用bos保存订单
	 */
	@Action("orderAction_add")
	public String save() throws Exception {
		try {
			//如果客户登陆
			Customer customer = (Customer) ServletActionContext.getRequest().getSession().getAttribute("loginCustomer");
			if(customer!=null){
				model.setCustomer_id(customer.getId());
			}
			Area area;
			if(StringUtils.isNotBlank(sendAreaInfo)){
				String[] strings = sendAreaInfo.split("/");
				String province = strings[0];
				String city = strings[1];
				String district = strings[2];
				area = new Area(province, city, district); 
				model.setSendArea(area);
			}
			if(StringUtils.isNotBlank(recAreaInfo)){
				String[] strings = recAreaInfo.split("/");
				String province = strings[0];
				String city = strings[1];
				String district = strings[2];
				area = new Area(province, city, district); 
				model.setRecArea(area);
			}
			orderProxy.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			return "order_fail";
		}
		return "order_success";
	}
	
}
