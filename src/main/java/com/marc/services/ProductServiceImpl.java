package com.marc.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.marc.domain.Product;

@Service
public class ProductServiceImpl implements ProductService {

	Map<Integer, Product> products;

	public ProductServiceImpl() {
		loadAllProducts();
	}

	@Override
	public List<Product> listAllProducts() {
		return new ArrayList<>(products.values());
	}

	@Override
	public Product getProductById(Integer id) {
		return products.get(id);
	}

	@Override
	public Product saveOrUpdateProduct(Product product) {
		if (product != null) {
			if (product.getId() == null) {
				product.setId(getNextKey());
			}
			products.put(product.getId(), product);
			return product;
		} else {
			throw new RuntimeException("Product cant be null");
		}
	}

	@Override
	public void deleteProduct(Integer id) {
		products.remove(id);

	}

	private Integer getNextKey() {
		return Collections.max(products.keySet()) + 1;
	}

	private void loadAllProducts() {
		products = new HashMap<>();
		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("Mouse");
		p1.setPrice(new BigDecimal(14.99));
		p1.setImageURL("http://example.com/product1");
		products.put(1, p1);

		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("Screen");
		p2.setPrice(new BigDecimal(99.99));
		p2.setImageURL("http://example.com/product1");
		products.put(2, p2);

	}
}
