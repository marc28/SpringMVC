package com.marc.services;

import java.util.List;

/**
 * Notice how the two interfaces ProductService and CustomerService EXTEND this
 * Interface with their respective Objects:
 * 	1) public interface ProductService extends CRUDService<Product>
 *	2) public interface CustomerService extends CRUDService<Customer>
 * 
 * 
 */
public interface CRUDService<T> {

	List<?> listAll();

	T getById(Integer id);

	T saveOrUpdate(T domainObject);

	void delete(Integer id);
}
