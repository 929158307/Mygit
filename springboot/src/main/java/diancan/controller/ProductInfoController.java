package diancan.controller;

import diancan.pojo.ProductInfo;
import diancan.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoServiceImpl;


    public List<ProductInfo> findByStatus(Integer status){
        return productInfoServiceImpl.findByProductStatus(status);
    }

    public List<ProductInfo> findAll(Integer pageNum,Integer pageSize){
        Pageable pageable = new PageRequest(pageNum,pageSize);
        Page<ProductInfo> page = productInfoServiceImpl.findAll(pageable);
        return page.getContent();

    }

}
