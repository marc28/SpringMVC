package com.marc.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.marc.domain.Customer;
@Service
public class CustomerServiceImple implements CustomerService {

	private Map<Integer, Customer> customers;

	public CustomerServiceImple() {
		fillCustomerList();
	}

	@Override
	public List<Customer> showAllCustomers() {
		return new ArrayList<Customer>(customers.values());
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customers.get(id);
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		if (customer != null) {
			if (customer.getId() == null) {
				customer.setId(nextId());
			}
			customers.put(customer.getId(), customer);
			return customer;
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteCustomer(Integer id) {
		customers.remove(id);
	}

	private Integer nextId() {
		return Collections.max(customers.keySet()) + 1;
	}

	private void fillCustomerList() {
		customers = new HashMap<>();
		Customer cus = new Customer();
		cus.setId(1);
		cus.setFirstName("Marc");
		cus.setLastName("Grogan");
		cus.setEmail("marc@mail.com");
		cus.setPhoneNo("123-456");
		cus.setAddressOne("clonwood");
		cus.setAddressTwo("Clane");
		cus.setCity("kildare");
		cus.setState("Leinster");
		cus.setZip("1H-2FG");
		customers.put(cus.getId(), cus);

	}

}
