package pinyougou.core.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pinyougou.core.dao.specification.SpecificationOptionDao;
import pinyougou.core.dao.template.TypeTemplateDao;
import pinyougou.core.pojo.specification.SpecificationOption;
import pinyougou.core.pojo.specification.SpecificationOptionQuery;
import pinyougou.core.pojo.specification.SpecificationQuery;
import pinyougou.core.pojo.template.TypeTemplate;
import pinyougou.core.serviceinterface.TypeTemplateService;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Autowired
    private TypeTemplateDao typeTemplateDaoimpl;
    @Autowired
    private SpecificationOptionDao specificationOptionDaoImpl;
    //分页查询
    @Override
    public PageResult find(Integer pageNumber, Integer pageSize, TypeTemplate typeTemplate){
        PageHelper.startPage(pageNumber,pageSize);
        Page<TypeTemplate> page = (Page<TypeTemplate>)typeTemplateDaoimpl.selectByExample(null);
        return new PageResult(page.getTotal(),page.getResult());
    }
    //添加
    @Override
    public void add(TypeTemplate typeTemplate){
        typeTemplateDaoimpl.insertSelective(typeTemplate);

    }
    @Override
    public void updata(TypeTemplate typeTemplate){
        typeTemplateDaoimpl.updateByPrimaryKeySelective(typeTemplate);
    }
    @Override
    public void delete(TypeTemplate typeTemplate){
        typeTemplateDaoimpl.updateByPrimaryKeySelective(typeTemplate);
    }

    @Override
    public TypeTemplate findOne(Long id) {
        return typeTemplateDaoimpl.selectByPrimaryKey(id);
    }
    //规格列表
    @Override
    public List<Map> findSpecOption(Long id){
        TypeTemplate typeTemplate = findOne(id);
        List<Map> list = JSON.parseArray(typeTemplate.getSpecIds(),Map.class);
        for(Map map : list){
            //每个map里面有 id  名字 和 规格的所有列表
            SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
            SpecificationOptionQuery.Criteria criteria = specificationOptionQuery.createCriteria();
            criteria.andSpecIdEqualTo((long)(Integer)map.get("id"));
            List<SpecificationOption> specificationOptions = specificationOptionDaoImpl.selectByExample(specificationOptionQuery);
            map.put("options",specificationOptions);
        }
        return list;
    }
}
