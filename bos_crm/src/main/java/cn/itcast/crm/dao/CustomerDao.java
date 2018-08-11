package cn.itcast.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.crm.domain.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

	//sql:select t.*, t.rowid from T_CUSTOMER t where t.c_fixed_area_id is null;
	List<Customer> findByFixedAreaIdIsNull();

	//sql:select t.*, t.rowid from T_CUSTOMER t where t.c_fixed_area_id = ?;
	List<Customer> findByFixedAreaId(String fixedAreaId);

	//解除关联
	@Query("update Customer set fixedAreaId = null where fixedAreaId = ?")
	@Modifying
	void setFixedareaId2Null(String fixedAreaId);

	//客户关联定区
	@Query("update Customer set fixedAreaId = ? where id = ?")
	@Modifying
	void assignCustomers2FixedArea(String fixedAreaId, Integer customerId);

	List<Customer> findByTelephone(String tel);

	@Query("update Customer set type = 1 where id = ?")
	@Modifying
	void activeAccount(Integer customerId);

	Customer findByTelephoneAndPassword(String tel, String pwd);

	@Query("select fixedAreaId from Customer where address = ?")
	String findFixedAreaIdByAddress(String address);

}
