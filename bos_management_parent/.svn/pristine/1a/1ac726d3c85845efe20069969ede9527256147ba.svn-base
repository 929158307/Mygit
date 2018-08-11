package cn.itcast.bos.web.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.service.base.AreaService;
import cn.itcast.bos.utils.PinYin4jUtils;
import cn.itcast.bos.web.action.common.BaseAction;


@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
@Results({
	@Result(name="list",type="redirect",location="/pages/base/area.jsp")
})
public class AreaAction extends BaseAction<Area> {

	private Area model = new Area();
	public Area getModel() {
		return model;
	}
	
	@Autowired
	private AreaService areaService;
	
	//接收文件参数名 必须跟表单中提交文件参数名一致
	private File upload;
	//获取文件名称 MIME类型
	private String uploadFileName;
	private String uploadContentType;
	public void setUpload(File upload) {
		this.upload = upload;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


	/**
	  * @Description: 1、接收上传文件  2、通过POI解析excel文件中数据  3、持久化到数据库
	*/
	@Action("areaAction_importXls")
	public String importXls() throws Exception {
		//創建excel文件對象
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(upload));
		
		//获取标签页
		HSSFSheet sheet = workbook.getSheetAt(0);
		//获取行
		List<Area> list = new ArrayList<>();
		Area area;
		for (Row row : sheet) {
			//忽略第一行 标题行
			if(row.getRowNum()==0){
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			
			
			
			area = new Area(id, province, city, district, postcode, null, null);
			
			
			//简码：HBSJZQD
			province = province.substring(0, province.length()-1);
			city = city.substring(0, city.length()-1);
			district = district.substring(0, district.length()-1);
			
			String all = province+city+district;
			
			String[] strings = PinYin4jUtils.getHeadByString(all);
			String shortcode = StringUtils.join(strings);
			area.setShortcode(shortcode);
			
			//城市编码：shijiazhuang
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			area.setCitycode(citycode);
			
			list.add(area);
		}
		areaService.save(list);
		workbook.close();
		
		return NONE;
	}
	
	/**
	  * @Description: 区域分页
	 */
	@Action("areaAction_pageQuery")
	public String pageQuery() throws Exception {
		Pageable pageable = new PageRequest(page-1, rows);
		Page<Area> page = areaService.pageQuery(pageable);
		this.java2Json(page, new String[]{"subareas"});
		return NONE;
	}
	
	/**
	 * @Description: 查询所有区域
	 */
	@Action("areaAction_findAll")
	public String findAll() throws Exception {
		List<Area> list = areaService.findAll();
		this.java2Json(list, new String[]{"subareas"});
		return NONE;
	}

}
