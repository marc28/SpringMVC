package com.marc.services;

import java.util.List;
import com.marc.domain.Customer;

public interface CustomerService {
	List<Customer> showAllCustomers();
	Customer getCustomerById(Integer id);
	Customer saveOrUpdateCustomer(Customer customer);
	void deleteCustomer(Integer id);

}
