package pinyougou.core.dao.template;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pinyougou.core.pojo.template.TypeTemplate;
import pinyougou.core.pojo.template.TypeTemplateQuery;

public interface TypeTemplateDao {
    int countByExample(TypeTemplateQuery example);

    int deleteByExample(TypeTemplateQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(TypeTemplate record);

    int insertSelective(TypeTemplate record);

    List<TypeTemplate> selectByExample(TypeTemplateQuery example);

    TypeTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TypeTemplate record, @Param("example") TypeTemplateQuery example);

    int updateByExample(@Param("record") TypeTemplate record, @Param("example") TypeTemplateQuery example);

    int updateByPrimaryKeySelective(TypeTemplate record);

    int updateByPrimaryKey(TypeTemplate record);
}