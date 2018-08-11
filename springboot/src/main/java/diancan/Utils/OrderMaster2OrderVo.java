package diancan.Utils;

import diancan.pojo.OrderMaster;
import diancan.vo.OrderVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderVo {
    public static OrderVo O2Ovo(OrderMaster orderMaster){
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(orderMaster, orderVo);
        return orderVo;
    }
    public static List<OrderVo> listO2listOvo(List<OrderMaster> list){
        return list.stream().map(e -> O2Ovo(e)).collect(Collectors.toList());
    }
}
