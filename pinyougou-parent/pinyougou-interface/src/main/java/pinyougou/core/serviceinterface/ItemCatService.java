package pinyougou.core.serviceinterface;

import pinyougou.core.pojo.item.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findParentId(Long id);

    void add(ItemCat itemCat);

    void updata(ItemCat itemCat);

    ItemCat findOne(Long id);

    List<ItemCat> findAll();
}
