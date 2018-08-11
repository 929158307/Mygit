package diancan.service;

import diancan.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);

    void save(ProductCategory productCategory);
}
