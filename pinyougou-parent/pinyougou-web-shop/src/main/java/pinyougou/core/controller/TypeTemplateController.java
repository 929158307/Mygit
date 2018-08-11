package pinyougou.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.template.TypeTemplate;
import pinyougou.core.serviceinterface.TypeTemplateService;

@RestController
@RequestMapping("/typetemlate")
public class TypeTemplateController {
    @Reference
    private TypeTemplateService typeTemplateServiceimpl;

    @RequestMapping("/find")
    public PageResult find(Integer pageNumber,Integer pageSize,@RequestBody TypeTemplate typeTemplate){
        return typeTemplateServiceimpl.find(pageNumber, pageSize,typeTemplate);
    }
    @RequestMapping("/add")
    public Result add(@RequestBody TypeTemplate typeTemplate){
        try {
            typeTemplateServiceimpl.add(typeTemplate);
            return new Result(true,"添加成功");
        } catch(Exception ex) {
            return new Result(false,"提交失败"); }


    }
    @RequestMapping("/updata")
    public Result updata(@RequestBody TypeTemplate typeTemplate){
        try {
            typeTemplateServiceimpl.updata(typeTemplate);
            return new Result(true,"更新成功");
        } catch(Exception ex) {
            return new Result(false,"更新失败"); }

    }
    @RequestMapping("/delete")
    public Result delete(@RequestBody TypeTemplate typeTemplate){
        try {
            typeTemplateServiceimpl.delete(typeTemplate);
            return new Result(true,"删除成功");
        } catch(Exception ex) {
            return new Result(false,"删除失败"); }

    }
    @RequestMapping("/findBrand")
    public TypeTemplate findOne(Long id){
        return  typeTemplateServiceimpl.findOne(id);
    }

}
