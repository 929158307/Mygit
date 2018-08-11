package pinyougou.core.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pinyougou.core.dao.seller.SellerDao;
import pinyougou.core.pojo.seller.Seller;
import pinyougou.core.pojo.seller.SellerQuery;
import pinyougou.core.serviceinterface.SellerService;

import java.util.List;


@Service
@Transactional
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerDao sellerDaoImpl;

    @Override
    public void add(Seller seller) {
        seller.setStatus("0");
        seller.setPassword(new BCryptPasswordEncoder().encode(seller.getPassword()));
        sellerDaoImpl.insertSelective(seller);
    }
    //根据单选的条件查询 以及 各种名字
    @Override
    public PageResult findByNumber(Integer pageNum, Integer pageSize, String status, Seller seller){
        PageHelper.startPage(pageNum,pageSize);
        SellerQuery sellerQuery = new SellerQuery();
        SellerQuery.Criteria criteria = sellerQuery.createCriteria();
        //根据给定的状态
        if(null!=status && !"".equals(status.trim())){
            criteria.andStatusEqualTo(status);
        }
        //根据两个名字
        if (null != seller.getName() && !"".equals(seller.getName().trim())) {
            criteria.andNameLike("%" + seller.getName() + "%");
        }
        if (null != seller.getNickName() && !"".equals(seller.getNickName().trim())) {
            criteria.andNickNameLike("%" + seller.getNickName() + "%");
        }
        Page<Seller> sellers = (Page<Seller>)sellerDaoImpl.selectByExample(sellerQuery);
        return new PageResult(sellers.getTotal(),sellers.getResult());
    }
    //商家查询默认的为0的
    @Override
    public PageResult find(Integer pageNum, Integer pageSize, Seller seller) {
        PageHelper.startPage(pageNum, pageSize);
        //设置查询条件，牧户查询，状态为传进来的 默认为0
        SellerQuery sellerQuery = new SellerQuery();
        SellerQuery.Criteria criteria = sellerQuery.createCriteria();
        if (null != seller.getName() && !"".equals(seller.getName().trim())) {
            criteria.andNameLike("%" + seller.getName() + "%");
        }
        if (null != seller.getNickName() && !"".equals(seller.getNickName().trim())) {
            criteria.andNickNameLike("%" + seller.getNickName() + "%");
        }
        //根据默认状态0
        criteria.andStatusEqualTo(seller.getStatus());
        Page<Seller> sellers = (Page<Seller>) sellerDaoImpl.selectByExample(sellerQuery);
        return new PageResult(sellers.getTotal(), sellers.getResult());
    }
    @Override
    public Seller findOne(String id) {
        return sellerDaoImpl.selectByPrimaryKey(id);
    }

    @Override
    public Seller findByName(String name) {
        SellerQuery sellerQuery = new SellerQuery();
        SellerQuery.Criteria criteria = sellerQuery.createCriteria();
        if(null!=name && !"".equals(name)){
            criteria.andNameLike(name);
        }
        return  sellerDaoImpl.selectByExample(sellerQuery).get(0);

    }

    //审核
    @Override
    public void auditing(String id, String status){
        Seller seller = new Seller();
        seller.setSellerId(id);
        seller.setStatus(status);
        sellerDaoImpl.updateByPrimaryKeySelective(seller);
    }
}
