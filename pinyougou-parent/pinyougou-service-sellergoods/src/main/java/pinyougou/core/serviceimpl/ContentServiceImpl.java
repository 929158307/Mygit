package pinyougou.core.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import pinyougou.core.dao.ad.ContentDao;
import pinyougou.core.pojo.ad.Content;
import pinyougou.core.pojo.ad.ContentQuery;
import pinyougou.core.serviceinterface.ContentService;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
	private ContentDao contentDao;

	@Override
	public List<Content> findAll() {
		List<Content> list = contentDao.selectByExample(null);
		return list;
	}

	@Override
	public PageResult findPage(Content content, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Content> page = (Page<Content>)contentDao.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void add(Content content) {
		contentDao.insertSelective(content);
	}

	@Override
	public void edit(Content content) {
		contentDao.updateByPrimaryKeySelective(content);
	}

	@Override
	public Content findOne(Long id) {
		Content content = contentDao.selectByPrimaryKey(id);
		return content;
	}

	@Override
	public void delAll(Long[] ids) {
		if(ids != null){
			for(Long id : ids){
				contentDao.deleteByPrimaryKey(id);
			}
		}
	}
	@Override
	public List<Content> findByCateId(Long id){
		ContentQuery contentQuery = new ContentQuery();
		ContentQuery.Criteria criteria = contentQuery.createCriteria();
		criteria.andCategoryIdEqualTo(id);
		contentQuery.setOrderByClause("sort_order desc");
		List<Content> contents = contentDao.selectByExample(contentQuery);
		return  contents;
	}

}