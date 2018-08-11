package cn.itcast.sms.listener;

import cn.itcast.bos.utils.AliSmsUtil;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.HashMap;
import java.util.Map;

//MapMessage mapMessage = session.createMapMessage();
//        mapMessage.setString("tel",model.getTelephone());
//        mapMessage.setString("model","SMS_119087143");
//        mapMessage.setObject("map",map);
@Component("sendMessage")
public class sendMessageListener implements MessageListener{

    @Override
    public void onMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage) message;
            String tel = mapMessage.getString("tel");
            String model = mapMessage.getString("model");
            HashMap<String,Object> map = (HashMap<String, Object>) mapMessage.getObject("map");
            Boolean flag = AliSmsUtil.sendMessage(tel, model, map);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
