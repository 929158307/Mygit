package pinyougou.core.dao.address;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pinyougou.core.pojo.address.Areas;
import pinyougou.core.pojo.address.AreasQuery;

public interface AreasDao {
    int countByExample(AreasQuery example);

    int deleteByExample(AreasQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Areas record);

    int insertSelective(Areas record);

    List<Areas> selectByExample(AreasQuery example);

    Areas selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Areas record, @Param("example") AreasQuery example);

    int updateByExample(@Param("record") Areas record, @Param("example") AreasQuery example);

    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);
}