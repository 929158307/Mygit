package pinyougou.core.serviceinterface;

import entity.PageResult;
import pinyougou.core.pojo.ad.ContentCategory;

import java.util.List;

public interface ContentCategoryService {

	public List<ContentCategory> findAll();

    public PageResult findPage(ContentCategory contentCategory, Integer pageNum, Integer pageSize);
	
	public void add(ContentCategory contentCategory);
	
	public void edit(ContentCategory contentCategory);
	
	public ContentCategory findOne(Long id);
	
	public void delAll(Long[] ids);
}