package pinyougou.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.grouppojo.SpecificationVo;
import pinyougou.core.pojo.specification.Specification;
import pinyougou.core.serviceinterface.SpecificationService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Reference
    private SpecificationService specificationServiceImpl;

    //增
    @RequestMapping("/add")
    public Result add(@RequestBody  SpecificationVo vo){
        try {
            specificationServiceImpl.add(vo);
            return new Result(true,"增加成功");
        } catch(Exception ex) { ex.printStackTrace(); }
        return new Result(false,"增加失败");
    }
    //删
    @RequestMapping("/delete")
    public Result delete(@RequestBody  SpecificationVo vo){
        try {
            specificationServiceImpl.delete(vo);
            return new Result(true,"删除成功");
        } catch(Exception ex) { ex.printStackTrace(); }
        return new Result(false,"删除失败");
    }
    //改
    @RequestMapping("/updata")
    public Result updata(@RequestBody SpecificationVo vo){
        try {
            specificationServiceImpl.updata(vo);
            return new Result(true,"更新成功");
        } catch(Exception ex) { ex.printStackTrace(); }
        return new Result(false,"更新失败");
    }
    //查
    //条件分页(不写就是全部)
    @RequestMapping("/find")
    public PageResult find(Integer pageNum, Integer pageSize, @RequestBody Specification specification){
        return specificationServiceImpl.find(pageNum,pageSize,specification);
    }
    //查一个
    @RequestMapping("/findOne")
    public SpecificationVo findOne(Long id){
        return specificationServiceImpl.findOne(id);
    }
    //下拉菜单所有规格
    @RequestMapping("/findSpecificationOption")
    public List<Map> findSpecificationOption(){
        return specificationServiceImpl.findSpecifcationOption();
    }


}
