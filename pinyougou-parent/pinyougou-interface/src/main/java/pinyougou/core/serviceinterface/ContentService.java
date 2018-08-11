package pinyougou.core.serviceinterface;

import entity.PageResult;
import pinyougou.core.pojo.ad.Content;

import java.util.List;

public interface ContentService {

	public List<Content> findAll();

    public PageResult findPage(Content content, Integer pageNum, Integer pageSize);
	
	public void add(Content content);
	
	public void edit(Content content);
	
	public Content findOne(Long id);
	
	public void delAll(Long[] ids);

	List<Content> findByCateId(Long id);
}