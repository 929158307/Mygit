package pinyougou.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.goodspojo.GoodsVo;
import pinyougou.core.pojo.seller.Seller;
import pinyougou.core.serviceinterface.GoodsService;
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

}
