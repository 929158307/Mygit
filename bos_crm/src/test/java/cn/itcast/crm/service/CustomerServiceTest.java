package cn.itcast.crm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.crm.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;

	@Test
	public void testFindNotAssociation() {
		List<Customer> list = customerService.findNotAssociation();
		System.out.println(list);
	}

	@Test
	public void testFindHasAssociation() {
		List<Customer> list = customerService.findHasAssociation("DQ001");
		System.out.println(list);
	}
	
	@Test
	public void assignCustomers2FixedArea() {
		String fixedAreaId = "DQ001";
		List<Integer> customerIds = new ArrayList<>();
		customerIds.add(1);
//		customerIds.add(2);
		
		customerService.assignCustomers2FixedArea(fixedAreaId , null );
	}

}
