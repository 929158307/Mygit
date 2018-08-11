package pinyougou.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.good.Goods;
import pinyougou.core.pojo.goodspojo.GoodsVo;
import pinyougou.core.serviceinterface.GoodsService;
import pinyougou.core.serviceinterface.TypeTemplateService;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Reference
    private GoodsService goodsServiceImpl;
    @Reference
    private TypeTemplateService typeTemplateServiceImpl;

    @RequestMapping("/addGoods")
    public Result addGoods(@RequestBody GoodsVo goodsVo){
        try {
            goodsServiceImpl.addGoods(goodsVo);
            return new Result(true,"添加成功");
        } catch(Exception ex) {
            return new Result(false,"添加失败"); }
    }
    @RequestMapping("/findlist")
    public PageResult findList(Integer page, Integer rows, @RequestBody Goods goods){
        return goodsServiceImpl.findlist(page,rows,goods);
    }
    @RequestMapping("/findOne")
    public GoodsVo findOne(Long id){
        return goodsServiceImpl.findOne(id);
    }

    //更新
    @RequestMapping("/updata")
    public void updata(@RequestBody GoodsVo goodsVo){
        goodsServiceImpl.updata(goodsVo);
    }
    //审核
    @RequestMapping("/updataStatus")
    public Result updataStatus(Long[] ids,String status){
        try {
            goodsServiceImpl.updataStatus(ids,status);
            return new Result(true,"提交成功");
        } catch(Exception ex) {
            return new Result(false,"提交失败"); }
    }
}
