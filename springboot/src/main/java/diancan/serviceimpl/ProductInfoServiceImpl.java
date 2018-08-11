package diancan.serviceimpl;

import diancan.dao.ProductInfoDao;
import diancan.pojo.ProductInfo;
import diancan.service.ProductInfoService;
import diancan.vo.CatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDaoImpl;

    @Override
    public List<ProductInfo> findByProductStatus(Integer status){
       return productInfoDaoImpl.findByProductStatus(status);
    }
    @Override
    public ProductInfo findOne(String productId){
        return productInfoDaoImpl.getOne(productId);
    }
    @Override
    public Page<ProductInfo> findAll(Pageable pageable){
        Page<ProductInfo> page = productInfoDaoImpl.findAll(pageable);
        return page;

    }
    @Override
    public void save(ProductInfo productInfo){
        productInfoDaoImpl.save(productInfo);
    }
    //增加库存
    @Override
    public void increase(List<CatVo> catVoList){
        for(CatVo catVo : catVoList){
            ProductInfo one = productInfoDaoImpl.getOne(catVo.getProductId());
            if(null == one){
                throw new RuntimeException("商品不存在");
            }
            Integer newStock = one.getProductStock() + catVo.getProductQuantity();
            one.setProductStock(newStock);
            productInfoDaoImpl.save(one);
        }
    }
    @Override
    //减少库存
    public void decrease(List<CatVo> catVoList) throws RuntimeException{
        for(CatVo catVo : catVoList){
            ProductInfo one = productInfoDaoImpl.getOne(catVo.getProductId());
            if(null == one){
                throw new RuntimeException("商品不存在");
            }
            Integer newStock = one.getProductStock() - catVo.getProductQuantity();
            if(newStock<0){
                throw new RuntimeException("库存不够");
            }else{
                one.setProductStock(newStock);
                productInfoDaoImpl.save(one);
            }

        }

    }



}
