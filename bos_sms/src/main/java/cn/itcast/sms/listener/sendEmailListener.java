package cn.itcast.sms.listener;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import cn.itcast.bos.utils.MailUtils;
import org.springframework.stereotype.Component;

import cn.itcast.bos.utils.AliSmsUtil;

@Component("sendEmail")
public class sendEmailListener implements MessageListener {

	/**
	  * @Description:读取sms_mq队列中发短信消息 
	  * @return 
	*/
//	mapMessage.setString("title","欢迎注册快递");
//							mapMessage.setString("text",content);
//							mapMessage.setString("email",model.getEmail());
	public void onMessage(Message message) {
		try {
			MapMessage mapMessage = (MapMessage) message;
			String title = mapMessage.getString("title");
			String content = mapMessage.getString("text");
			String email = mapMessage.getString("email");
			MailUtils.sendMail(title,content,email);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
