package pinyougou.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.good.Brand;
import pinyougou.core.serviceinterface.BrandService;

import java.util.List;
import java.util.Map;

//   /brand/test.do
//   /admin/brand.html
@RestController//等于@reponsebody + controller
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandServiceImpl;

    //测试连接
    @RequestMapping("/test")
    public void  test(){
        System.out.println("test");
        try {
            brandServiceImpl.test();
        } catch(Exception ex) {
            System.out.println("未找到暴露的方法");
        }
    }

    @RequestMapping("/findAll")
    public List<Brand> findAll(){
        return brandServiceImpl.findAll();
    }

    @RequestMapping("/addBrand")
    public Result addBrand(@RequestBody Brand brand){
        try {
            int flag = brandServiceImpl.add(brand);
            if(flag>0){
                return new Result(true,"添加成功");
            }else {
                return new Result(false,"添加失败");
            }
        } catch(Exception ex) {
            System.out.println("远程调用失败，可能是找不到暴露的借口，请查看zookeeper，和服务提供方");
            return new Result(false,"添加失败");
        }
    }
    @RequestMapping("/deleteBrand")
    public Result delate(long id){
        try {
            int flag = brandServiceImpl.delete(id);
            if(flag>0){
                return new Result(true,"删除成功");
            }else {
                return new Result(false,"删除失败");
            }
        } catch(Exception ex) {
            System.out.println("远程调用失败，可能是找不到暴露的借口，请查看zookeeper，和服务提供方");
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/updataBrand")
    public Result updataBrand(@RequestBody Brand brand) {
        try {
            int flag = brandServiceImpl.update(brand);
            if (flag > 0) {
                return new Result(true, "更新成功");
            } else {
                return new Result(false, "更新失败");
            }
        } catch (Exception ex) {
            System.out.println("远程调用失败，可能是找不到暴露的借口，请查看zookeeper，和服务提供方");
            return new Result(false, "更新失败");
        }
    }
    @RequestMapping("/findAllByExemple")
    public PageResult findAllByExemple(Integer page,Integer rows,@RequestBody Brand brand){
        return brandServiceImpl.findAllByExemple(page,rows,brand);
    }

    @RequestMapping("/findListByPage")
    public PageResult findListByPage(Integer pageNum,Integer pageSize){
        PageResult pageResult = brandServiceImpl.findAllByPage(pageNum,pageSize);
        return pageResult;
    }
    @RequestMapping("/findOne")
    public Brand findOne(Long id){
        return brandServiceImpl.findOne(id);
    }
    //查询下拉框结果街
    //select2标签需要个map id 对应 text
    @RequestMapping("/findOptionList")
    public List<Map> findOptionList(){
        return brandServiceImpl.findOptionList();
    }


}
