package pinyougou.core.dao.good;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pinyougou.core.pojo.good.Brand;
import pinyougou.core.pojo.good.BrandQuery;

public interface BrandDao {
    int countByExample(BrandQuery example);

    int deleteByExample(BrandQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandQuery example);

    Brand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandQuery example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandQuery example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
}