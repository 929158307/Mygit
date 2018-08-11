package pinyougou.core.dao.item;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pinyougou.core.pojo.item.Item;
import pinyougou.core.pojo.item.ItemQuery;

public interface ItemDao {
    int countByExample(ItemQuery example);

    int deleteByExample(ItemQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemQuery example);

    Item selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemQuery example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemQuery example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}