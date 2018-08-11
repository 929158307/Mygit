package pinyougou.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.seller.Seller;
import pinyougou.core.serviceinterface.SellerService;

@RestController
@RequestMapping("/Seller")
public class SellerController {
    @Reference
    private SellerService sellerServiceImpl;

    @RequestMapping("/add")
    public Result add(@RequestBody Seller seller){
        try {
            sellerServiceImpl.add(seller);
            return new Result(true,"添加成功");
        } catch(Exception ex) {
            return new Result(false,"添加失败"); }
    }
    //按照 公司名字 和 店铺名字查询
    @RequestMapping("/find")
    public PageResult find(Integer pageNum,Integer pageSize,@RequestBody Seller seller){
        return sellerServiceImpl.find(pageNum,pageSize,seller);

    }
    //按照给定状态查询 以及店铺的名字 和 公司名字
    @RequestMapping("/findbynumber")
    public PageResult findByNumber(Integer pageNum,Integer pageSize,String status,@RequestBody Seller seller){
        return sellerServiceImpl.findByNumber(pageNum,pageSize,status,seller);
    }
    @RequestMapping("/findOne")
    public Seller findOne(String id){
        return sellerServiceImpl.findOne(id);
    }
    @RequestMapping("/auditing")
    public Result auditing(String sellerId,String status){
        try {
         sellerServiceImpl.auditing(sellerId,status);
         return new Result(true,"审核成功");
        } catch(Exception ex) {  return new Result(false,"审核失败"); }

    }

}
