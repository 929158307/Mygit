package diancan.serviceimpl;

import diancan.dao.ProductCategoryDao;
import diancan.pojo.ProductCategory;
import diancan.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDaoImpl;

    @Override
    public ProductCategory findOne(Integer id){
        return  productCategoryDaoImpl.getOne(id);
    }
    @Override
    public List<ProductCategory> findAll(){
        return productCategoryDaoImpl.findAll();
    }
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList){
        return productCategoryDaoImpl.findByCategoryTypeIn(categoryList);
    }
    @Override
    public void save(ProductCategory productCategory){
        productCategoryDaoImpl.save(productCategory);
    }

}
