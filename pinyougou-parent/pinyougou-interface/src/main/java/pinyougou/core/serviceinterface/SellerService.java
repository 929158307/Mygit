package pinyougou.core.serviceinterface;

import entity.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import pinyougou.core.pojo.seller.Seller;

public interface SellerService {
    void add(Seller seller);

    //根据单选的条件查询 以及 各种名字
    PageResult findByNumber(Integer pageNum, Integer pageSize, String status, Seller seller);

    PageResult find(Integer pageNum, Integer pageSize, @RequestBody Seller seller);

    Seller findOne(String id);

    Seller findByName(String name);

    void auditing(String id, String status);

}
