package diancan.service;

import diancan.pojo.ProductInfo;
import diancan.vo.CatVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    List<ProductInfo> findByProductStatus(Integer status);

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    void save(ProductInfo productInfo);


    //增加库存
    void increase(List<CatVo> catVoList);

    //减少库存
    void decrease(List<CatVo> catVoList);
}
