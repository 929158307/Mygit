package diancan.controller;

import diancan.Utils.OrderForm2OrderVo;
import diancan.form.OrderForm;
import diancan.service.OrderService;
import diancan.vo.OrderVo;
import diancan.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/buyorder")
public class BuyOrderController {
    @Autowired
    private OrderService orderServiceImpl;

    @RequestMapping("/create")
    //创建订单
    public ResultVo create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        OrderVo orderVo = OrderForm2OrderVo.of2ov(orderForm, new OrderVo());
        try {
            orderServiceImpl.createOrder(orderVo);
            return new ResultVo(1,"添加成功");
        } catch(Exception ex) { return new ResultVo(0,"添加失败"); }
    }
    @RequestMapping("/findList")
    public ResultVo findList(String openid,Integer pageNum,Integer pageSize){
        Pageable pageable = new PageRequest(pageNum-1,pageSize);
        Page<OrderVo> list = orderServiceImpl.findList(openid, pageable);
        return new ResultVo(list.getContent());
    }
}
