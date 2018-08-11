package pinyougou.core.serviceinterface;

import entity.PageResult;
import pinyougou.core.pojo.template.TypeTemplate;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService {
    //分页查询
    PageResult find(Integer pageNumber, Integer pageSize, TypeTemplate typeTemplate);

    //添加
    void add(TypeTemplate typeTemplate);

    void updata(TypeTemplate typeTemplate);

    void delete(TypeTemplate typeTemplate);

    TypeTemplate findOne(Long id);

    List<Map> findSpecOption(Long id);
}
