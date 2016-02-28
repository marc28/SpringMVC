package com.marc.services;

import com.marc.domain.Customer;

/**
 * Note here how CustomerService EXTENDS CRUDService<T> and the <T> is replaced by Customer
 * 
 */
public interface CustomerService extends CRUDService<Customer>{
	
}
