package pinyougou.core.serviceinterface;

import entity.PageResult;
import pinyougou.core.pojo.good.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    void test();

    List<Brand> findAll();

    int add(Brand brand);

    int delete(long key);

    int update(Brand brand);

    PageResult findAllByPage(Integer pageNum, Integer pageSize);

    PageResult findAllByExemple(Integer pageNum, Integer pageSize, Brand brand);

    List<Map> findOptionList();

    Brand findOne(Long id);
}
