package com.marc.services;

import java.util.List;

import com.marc.domain.Product;

//@Component
public interface ProductService {

	List<Product> listAllProducts();
	Product getProductById(Integer id);
	Product saveOrUpdateProduct(Product product);
}
