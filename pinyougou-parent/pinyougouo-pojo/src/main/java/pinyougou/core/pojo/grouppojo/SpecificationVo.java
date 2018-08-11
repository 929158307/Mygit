package pinyougou.core.pojo.grouppojo;

import pinyougou.core.pojo.specification.Specification;
import pinyougou.core.pojo.specification.SpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * 包装对象
 * 组合对象
 * @author lx
 *
 */
public class SpecificationVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//规格对象
	private Specification specification;
	//规格属性结果集对象
	private List<SpecificationOption> SpecificationOptionList;
	public Specification getSpecification() {
		return specification;
	}
	public void setSpecification(Specification specification) {
		this.specification = specification;
	}
	public List<SpecificationOption> getSpecificationOptionList() {
		return SpecificationOptionList;
	}
	public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
		SpecificationOptionList = specificationOptionList;
	}
	
	
	

}
