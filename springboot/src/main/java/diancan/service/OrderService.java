package diancan.service;

import diancan.vo.OrderVo;
import org.springframework.data.domain.Page;

public interface OrderService {
    //创建订单
    void createOrder(OrderVo orderVo) throws RuntimeException;

    //查询单个订单
    OrderVo findOn(String orderId)throws RuntimeException;

    //查询订单列表
    Page<OrderVo> findList(String buyerOpenId, org.springframework.data.domain.Pageable pageable);


    //取消订单
    OrderVo cancel(OrderVo orderVo) throws RuntimeException;

    //完结订单
    OrderVo finish(OrderVo orderVo);

    //支付订单
    OrderVo paid(OrderVo orderVo)throws RuntimeException;
}
