package com.marc.services;

import java.util.List;

import com.marc.domain.Product;

public interface ProductService {

	List<Product> listAllProducts();
	Product getProductById(Integer id);
	Product saveOrUpdateProduct(Product product);
	void deleteProduct(Integer id);
}
