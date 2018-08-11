package pinyougou.core.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pinyougou.core.dao.good.BrandDao;
import pinyougou.core.pojo.good.Brand;
import pinyougou.core.pojo.good.BrandQuery;
import pinyougou.core.serviceinterface.BrandService;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {


    @Autowired
    private BrandDao brandDaoImpl;
    //测试连接
    @Override
    public void test(){
        System.out.println("test");
    }
    //测试连接

    @Override
    public int add(Brand brand) {
         return brandDaoImpl.insertSelective(brand);
    }
    @Override
    public int delete(long key){
        return brandDaoImpl.deleteByPrimaryKey(key);
    }
    @Override
    public int update(Brand brand){
        return brandDaoImpl.updateByPrimaryKey(brand);
    }
    //查询全部
    @Override
    public List<Brand> findAll(){
        return brandDaoImpl.selectByExample(null);
    }
    //分页
    @Override
    public PageResult findAllByPage(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        //给逆向工程的后面加入limit 这是上面那句话的作用，执行了就等于select * from 表 limit pagenum pagenum-1*size
        Page<Brand> page = (Page<Brand>) brandDaoImpl.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }
   //条件分页
    @Override
    public PageResult findAllByExemple(Integer pageNum, Integer pageSize, Brand brand){
        PageHelper.startPage(pageNum,pageSize);
        BrandQuery brandQuery = new BrandQuery();
        BrandQuery.Criteria criteria = brandQuery.createCriteria();
        if(null != brand.getName() && !"".equals(brand.getName().trim())){
            criteria.andNameLike("%"+brand.getName().trim()+"%");
        }
        if(null != brand.getFirstChar() && !"".equals(brand.getFirstChar().trim())){
            criteria.andFirstCharEqualTo(brand.getFirstChar().trim());
        }
        Page<Brand> brands = (Page<Brand>)brandDaoImpl.selectByExample(brandQuery);
        return new PageResult(brands.getTotal(),brands.getResult());
    }
    //查询一个
    @Override
    public Brand findOne(Long id) {
        return brandDaoImpl.selectByPrimaryKey(id);
    }
    @Override
    public List<Map> findOptionList() {
        return brandDaoImpl.findOptionList();
    }

}
