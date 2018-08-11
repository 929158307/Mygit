package pinyougou.core.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pinyougou.core.dao.item.ItemCatDao;
import pinyougou.core.pojo.item.ItemCat;
import pinyougou.core.pojo.item.ItemCatQuery;
import pinyougou.core.serviceinterface.ItemCatService;

import java.util.List;

@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    private ItemCatDao itemCatDaoImpl;
    //查询商品分类
    @Override
    public List<ItemCat> findParentId(Long id){
        ItemCatQuery query = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = query.createCriteria();
        criteria.andParentIdEqualTo(id);
        return itemCatDaoImpl.selectByExample(query);
    }
    //新增商品分类
    @Override
    public void add(ItemCat itemCat) {
        itemCatDaoImpl.insert(itemCat);
    }
    //更新商品分类信息
    @Override
    public void updata(ItemCat itemCat) {
        itemCatDaoImpl.deleteByPrimaryKey(itemCat.getId());
        itemCatDaoImpl.insert(itemCat);
    }

    @Override
    public ItemCat findOne(Long id) {
        return itemCatDaoImpl.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemCat> findAll() {
        return itemCatDaoImpl.selectByExample(null);
    }
}
