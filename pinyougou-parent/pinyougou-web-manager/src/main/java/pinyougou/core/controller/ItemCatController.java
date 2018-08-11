package pinyougou.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.item.ItemCat;
import pinyougou.core.serviceinterface.ItemCatService;

import java.util.List;

@RestController
@RequestMapping("/itemcat")
public class ItemCatController {
    @Reference
    private ItemCatService itemCatServiceImpl;

    @RequestMapping("/findParent")
    public List<ItemCat> findParent(Long parentId){
        return itemCatServiceImpl.findParentId(parentId);
    }
    //商品分类增加
    @RequestMapping("/add")
    public Result add(@RequestBody ItemCat itemCat) {
        try {
            itemCatServiceImpl.add(itemCat);
            return new Result(true, "添加成功");
        } catch (Exception ex) {
            return new Result(false, "添加失败");
        }
    }
    //商品分类更新
    @RequestMapping("/updata")
    public Result updata(@RequestBody ItemCat itemCat) {
        try {
            itemCatServiceImpl.updata(itemCat);
            return new Result(true, "更新成功");
        } catch (Exception ex) {
            return new Result(false, "更新失败");
        }
    }
}
