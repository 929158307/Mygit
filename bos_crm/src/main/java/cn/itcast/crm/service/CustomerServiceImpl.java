package cn.itcast.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.crm.dao.CustomerDao;
import cn.itcast.crm.domain.Customer;

/**
 * @Description: 
 *
 * @Title: CustomerServiceImpl.java
 * @date 2018年6月19日 上午11:53:34
 */

@Service("customerService")   //默认bean的id : customerServiceImpl
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public List<Customer> findNotAssociation() {
		return customerDao.findByFixedAreaIdIsNull();
	}

	public List<Customer> findHasAssociation(String fixedAreaId) {
		return customerDao.findByFixedAreaId(fixedAreaId);
	}

	/**
	  * @Description: 定区关联客户
	  * @param fixedAreaId:定区id
	  * @param customerIds :客户id(最终要将这些客户关联到提交定区)
	*/
	public void assignCustomers2FixedArea(String fixedAreaId, List<Integer> customerIds) {
		//方式一：1、根据定区id查询旧关联客户id  2、判断提交客户哪些是新增（将外键改为提交定区id）,哪些是要解除（将外键改为null）
		//方式二：1、根据定区id将关联旧客户解除关系  2、分别让提交客户关联定区
		customerDao.setFixedareaId2Null(fixedAreaId);
		
		if(customerIds!=null && customerIds.size()>0){
			for (Integer customerId : customerIds) {
				customerDao.assignCustomers2FixedArea(fixedAreaId, customerId);
				
			}
		}
		
	}

	public void regist(Customer customer) {
		customerDao.save(customer);
	}

	/**
	  * @Description: @return   true：验证通过   false:验证不通过
	*/
	public Boolean validateTel(String tel) {
		List<Customer> list = customerDao.findByTelephone(tel);
		if(list!=null && list.size()>0){
			return false;
		}
		return true;
	}

	public Customer findByType(String tel) {
		List<Customer> list = customerDao.findByTelephone(tel);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void activeAccount(Integer customerId) {
		customerDao.activeAccount(customerId);
	}

	public Customer login(String tel, String pwd) {
		return customerDao.findByTelephoneAndPassword(tel, pwd);
	}

	public String findFixedAreaIdByAddress(String address) {
		return customerDao.findFixedAreaIdByAddress(address);
	}

}
