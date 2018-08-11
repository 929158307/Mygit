package diancan.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//商品，包含类目
public class ProductVo {
    @JsonProperty("name")
    private String categoryname;
    @JsonProperty("type")
    private Integer categorytype;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Integer getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(Integer categorytype) {
        this.categorytype = categorytype;
    }

    public List<ProductInfoVo> getProductInfoVoList() {
        return productInfoVoList;
    }

    public void setProductInfoVoList(List<ProductInfoVo> productInfoVoList) {
        this.productInfoVoList = productInfoVoList;
    }
}
