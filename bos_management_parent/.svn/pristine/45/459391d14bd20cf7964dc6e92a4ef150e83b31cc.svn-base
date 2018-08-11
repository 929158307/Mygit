package cn.itcast.bos.web.action.base;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.base.SubAreaService;
import cn.itcast.bos.web.action.common.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
@Results({
	@Result(name="list",type="redirect",location="/pages/base/sub_area.jsp")
})
public class SubAreaAction extends BaseAction<SubArea> {

	@Autowired
	private SubAreaService subAreaService;
	
	/**
	  * @Description: 分区保存
	  * @return
	  * @throws Exception
	  *	  
	 */
	@Action("subareaAction_save")
	public String save() throws Exception {
		subAreaService.save(model);
		return "list";
	}
	
	@Action("subAreaAction_pageQuery")
	public String pageQuery() throws Exception {
		Pageable pageable = new PageRequest(page-1, rows);
		Page<SubArea> page = subAreaService.pageQuery(pageable);
		//noSession-转SubArea对象（对象中包含区域对象Area,在区域对象中包含subareas集合）
		//TODO 后期分区记录跟定区关联，还有有no-session
		this.java2Json(page, new String[]{"subareas"});
		return NONE;
	}
	
	/**
	  * @Description: 查询未关联到定区分区记录
	  * @return json数组
	  * @throws Exception
	  *	  
	 */
	@Action("subAreaAction_listajax")
	public String listajax() throws Exception {
		List<SubArea> list = subAreaService.findNotAssocaiton();
		this.java2Json(list, new String[]{"subareas"});
		return NONE;
	}
}
