package cn.itcast.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.itcast.bos.domain.base.SubArea;

public interface SubAreaDao extends JpaRepository<SubArea, String> {

	//sql:select t.*, t.rowid from T_SUB_AREA t where t.c_fixedarea_id is null;
	List<SubArea> findByFixedAreaIsNull();

}
