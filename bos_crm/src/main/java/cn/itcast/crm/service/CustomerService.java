package cn.itcast.crm.service;

import java.util.List;

import javax.jws.WebService;

import cn.itcast.crm.domain.Customer;

@WebService
public interface CustomerService {

	public List<Customer> findAll();
	
	
	//	查询未关联到定区的客户数据
	public List<Customer> findNotAssociation();
	
	//	查询关联到某个定区的客户数据
	public List<Customer> findHasAssociation(String fixedAreaId);
	
	
	//定区关联客户
	public void assignCustomers2FixedArea(String fixedAreaId, List<Integer> customerIds);
	
	
	//客户注册
	public void regist(Customer customer);
	
	//验证手机号是否存在
	public Boolean validateTel(String tel);
	
	//查询账户激活状态
	public Customer findByType(String tel);
	
	public void activeAccount(Integer customerId);
	
	//客户登陆
	public Customer login(String tel, String pwd);
	
	//分单使用：根据地址查询定区ID
	public String findFixedAreaIdByAddress(String address);
	
}
