package diancan.controller;

import diancan.pojo.ProductCategory;
import diancan.pojo.ProductInfo;
import diancan.service.ProductCategoryService;
import diancan.service.ProductInfoService;
import diancan.vo.ProductInfoVo;
import diancan.vo.ProductVo;
import diancan.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoServiceImpl;

    @Autowired
    private ProductCategoryService productCategoryServiceImpl;

    //查询上架
    @RequestMapping("/list")
    public ResultVo findList(){
        List<ProductInfo> productInfoList = productInfoServiceImpl.findByProductStatus(0);
        List<Integer> collect = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> CategoryType = productCategoryServiceImpl.findByCategoryTypeIn(collect);
        List<ProductVo> list = new ArrayList<>();
        for(ProductCategory categoryType : CategoryType){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryname(categoryType.getCategoryName());
            productVo.setCategorytype(categoryType.getCategoryType());
            //往热门分类地下放置
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                ProductInfoVo productInfoVo = new ProductInfoVo();
                BeanUtils.copyProperties(productInfo,productInfoVo);
                productInfoVos.add(productInfoVo);
            }
            productVo.setProductInfoVoList(productInfoVos);
            list.add(productVo);
        }
        return new ResultVo(list);
    }
}
