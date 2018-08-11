package pinyougou.core.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pinyougou.core.dao.specification.SpecificationDao;
import pinyougou.core.dao.specification.SpecificationOptionDao;
import pinyougou.core.pojo.grouppojo.SpecificationVo;
import pinyougou.core.pojo.specification.Specification;
import pinyougou.core.pojo.specification.SpecificationOption;
import pinyougou.core.pojo.specification.SpecificationOptionQuery;
import pinyougou.core.pojo.specification.SpecificationQuery;
import pinyougou.core.serviceinterface.SpecificationService;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService{

    @Autowired
    private SpecificationDao specificationDaoImpl;
    @Autowired
    private SpecificationOptionDao specificationOptionDaoImpl;

    //根据查询
    @Override
    public PageResult find(Integer pageNum, Integer pageSize, Specification specification){
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.orderBy("id desc");
        SpecificationQuery specificationQuery = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
        if(null != specification.getSpecName().trim() && !"".equals(specification.getSpecName().trim())){
            criteria.andSpecNameEqualTo(specification.getSpecName());
        }
        Page<Specification> specifications = (Page<Specification>)specificationDaoImpl.selectByExample(specificationQuery);
        return new PageResult(specifications.getTotal(),specifications.getResult());
    }
    //查询一个
    @Override
    public SpecificationVo findOne(Long id){
        SpecificationVo specificationVo = new SpecificationVo();
        //查一个规格模板
        specificationVo.setSpecification(specificationDaoImpl.selectByPrimaryKey(id));
        //创建查询条件，以规格模板的id 去查 规格表里面所有 该规格的模板
        SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = specificationOptionQuery.createCriteria();
        criteria.andSpecIdEqualTo(id);
        //该模板下所有的规格
        specificationVo.setSpecificationOptionList(specificationOptionDaoImpl.selectByExample(specificationOptionQuery));
        return specificationVo;
    }
    //添加规格
    @Override
    public void add(SpecificationVo vo){
        //添加规格模板
        specificationDaoImpl.insert(vo.getSpecification());
        //添加规格选项
        List<SpecificationOption> specificationOptionList = vo.getSpecificationOptionList();
        for(SpecificationOption specificationOption : specificationOptionList){
            //给每个规格选项 绑定 规格模板id
            specificationOption.setSpecId(vo.getSpecification().getId());
            //添加规格
            specificationOptionDaoImpl.insertSelective(specificationOption);
        }
    }
    @Override
    public void updata(SpecificationVo vo){
        //更新对应的规格 对应的信息
        specificationDaoImpl.updateByPrimaryKeySelective(vo.getSpecification());
        //删除对应的所有规格选项，再从新添加
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        criteria.andSpecIdEqualTo(vo.getSpecification().getId());
        //删除
        specificationOptionDaoImpl.deleteByExample(query);
        //获得新的规格选项
        List<SpecificationOption> specificationOptionList = vo.getSpecificationOptionList();
        for(SpecificationOption specificationOption : specificationOptionList){
            specificationOption.setSpecId(vo.getSpecification().getId());
            specificationOptionDaoImpl.insertSelective(specificationOption);
        }
    }
    @Override
    public void delete(SpecificationVo vo){
        //创建删除条件，线上字表 规格选项彪
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        criteria.andSpecIdEqualTo(vo.getSpecification().getId());
        specificationOptionDaoImpl.deleteByExample(query);
        //删除字表后在删除目标的id
        specificationDaoImpl.deleteByPrimaryKey(vo.getSpecification().getId());
    }
    @Override
    public List<Map> findSpecifcationOption(){
        return specificationDaoImpl.findSpecifcationOption();
    }

}
