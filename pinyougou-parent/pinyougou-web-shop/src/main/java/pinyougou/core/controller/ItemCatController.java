package pinyougou.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.item.ItemCat;
import pinyougou.core.pojo.template.TypeTemplate;
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

    @RequestMapping("/findOnew")
    public ItemCat findOne(Long id){
       return itemCatServiceImpl.findOne(id);
    }
    @RequestMapping("/findAll")
    public List<ItemCat> findAll(){
        return itemCatServiceImpl.findAll();
    }
}
