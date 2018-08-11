package diancan.serviceimpl;

import com.sun.xml.internal.bind.v2.TODO;
import diancan.Utils.IdCreater;
import diancan.Utils.OrderMaster2OrderVo;
import diancan.dao.OrderDetailDao;
import diancan.dao.OrderMasterDao;
import diancan.dao.ProductInfoDao;
import diancan.enums.OrderStatusEnum;
import diancan.enums.PayStatusEnums;
import diancan.pojo.OrderDetail;
import diancan.pojo.OrderMaster;
import diancan.pojo.ProductInfo;
import diancan.service.OrderService;
import diancan.service.ProductInfoService;
import diancan.vo.CatVo;
import diancan.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailDao orderDetailDaoImpl;
    @Autowired
    private OrderMasterDao orderMasterDaoImpl;
    @Autowired
    private ProductInfoService productInfoServiceImpl;
    @Autowired
    private ProductInfoDao productInfoDaoImpl;

    @Override
    //创建订单
    public void createOrder(OrderVo orderVo) throws RuntimeException{
        String orderId = IdCreater.creater();
        //查询商品(数量，价格)
        double total = 0.0;
        for(OrderDetail orderDetail : orderVo.getOrderDetailList()){
            ProductInfo one = productInfoDaoImpl.getOne(orderDetail.getProductId());
            if(null == one){
                throw new RuntimeException("查询不到对应产品");
            }
            //计算总价
            total += orderDetail.getProductQuantity()*one.getProductPrice();
            //设置细节信息
            BeanUtils.copyProperties(one,orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(IdCreater.creater());
            //写入OrderDetail
            orderDetailDaoImpl.save(orderDetail);
            //扣库存
            List<CatVo> CatVoList = orderVo.getOrderDetailList().stream().map(e ->
                    new CatVo(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
            try {
                productInfoServiceImpl.decrease(CatVoList);
            } catch(Exception ex) { ex.printStackTrace(); }
        }
        //写入订单数据库(orderMaster)
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(total);
        BeanUtils.copyProperties(orderVo,orderMaster);
        orderMasterDaoImpl.save(orderMaster);

    }
    @Override
    //查询单个订单
    public OrderVo findOn(String orderId)throws RuntimeException{
        OrderVo orderVo = new OrderVo();
        OrderMaster orderMaster = orderMasterDaoImpl.findByOrderId(orderId);
        if(null == orderMaster){
            throw new RuntimeException("没有该订单");
        }
        List<OrderDetail> orderDetails = orderDetailDaoImpl.findByOrderId(orderId);
        if(null == orderDetails){
            throw  new RuntimeException("没有该订单");
        }
        BeanUtils.copyProperties(orderMaster, orderVo);
        orderVo.setOrderDetailList(orderDetails);
        return orderVo;
    }
    @Override
    //查询订单列表
    public Page<OrderVo> findList(String buyerOpenId, Pageable pageable){
        Page<OrderMaster> pageOrderMaster = orderMasterDaoImpl.findByBuyerOpenid(buyerOpenId,pageable);
        List<OrderVo> list = OrderMaster2OrderVo.listO2listOvo(pageOrderMaster.getContent());
        Page<OrderVo> page = new PageImpl<OrderVo>(list,pageable,pageOrderMaster.getTotalElements());
        return page;
    }
    @Override
    //取消订单
    public OrderVo cancel(OrderVo orderVo) throws RuntimeException{
        //判断订单状态
        if(!OrderStatusEnum.NEW.equals(orderVo.getOrderStatus())){
            //订单已取消
            throw  new RuntimeException("订单无法取消");
        }
        //修改订单
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderVo,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster update = orderMasterDaoImpl.save(orderMaster);
        if (null == update){
            throw new RuntimeException("更新失败");
        }
        //返回库存
        if(orderVo.getOrderDetailList() == null){
            throw new RuntimeException("订单没商品");
        }
        List<CatVo> CatVoList = orderVo.getOrderDetailList().stream().map(e ->
                new CatVo(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoServiceImpl.increase(CatVoList);
        // 判断支付 支付 退款
        if("1".equals(orderVo.getPayStatus())){
            //TODO
        }
        return orderVo;
    }
    @Override
    //完结订单
    public OrderVo finish(OrderVo orderVo)throws RuntimeException{
        //判断订单状态
        if(!OrderStatusEnum.NEW.equals(orderVo.getOrderStatus())){
            throw new RuntimeException("不能改变状态");
        }else{
            orderVo.setOrderStatus(OrderStatusEnum.FINISH.getCode());
            OrderMaster orderMaster = new OrderMaster();
            BeanUtils.copyProperties(orderVo,orderMaster);
            orderMasterDaoImpl.save(orderMaster);
        }
        return orderVo;
    }
    @Override
    //支付订单
    public OrderVo paid(OrderVo orderVo)throws RuntimeException{
        //判断订单状态
        if(!OrderStatusEnum.NEW.equals(orderVo.getOrderStatus())) {
            throw new RuntimeException("不能改变状态");
        }
        if(!PayStatusEnums.WATE.equals(orderVo.getPayStatus())){
            throw new RuntimeException("订单已支付");
        }
        orderVo.setOrderStatus(PayStatusEnums.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderVo,orderMaster);
        orderMasterDaoImpl.save(orderMaster);
        return orderVo;
    }
}
