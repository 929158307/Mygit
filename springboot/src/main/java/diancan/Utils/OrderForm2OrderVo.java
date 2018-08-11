package diancan.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import diancan.form.OrderForm;
import diancan.pojo.OrderDetail;
import diancan.vo.OrderVo;

import java.util.List;

public class OrderForm2OrderVo {
    public static OrderVo of2ov(OrderForm orderForm, OrderVo orderVo){
        orderVo.setBuyerName(orderForm.getName());
        orderVo.setBuyerPhone(orderForm.getPhone());
        orderVo.setBuyerOpenid(orderForm.getOpenid());
        orderVo.setBuyerAddress(orderForm.getAddress());
        orderVo.setOrderDetailList(JSON.parseArray(orderForm.getItems(), OrderDetail.class));
        return orderVo;
    }
}
