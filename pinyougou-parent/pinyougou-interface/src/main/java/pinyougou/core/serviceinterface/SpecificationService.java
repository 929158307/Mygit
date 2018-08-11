package pinyougou.core.serviceinterface;

import entity.PageResult;
import pinyougou.core.pojo.grouppojo.SpecificationVo;
import pinyougou.core.pojo.specification.Specification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

    //根据查询
    PageResult find(Integer pageNum, Integer pageSize, Specification specification);

    //查询一个
    SpecificationVo findOne(Long id);

    //添加规格
    void add(SpecificationVo vo);

    void updata(SpecificationVo vo);

    void delete(SpecificationVo vo);

    List<Map> findSpecifcationOption();
}
