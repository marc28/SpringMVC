package com.marc.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marc.domain.DomainObject;
import com.marc.domain.Product;

@Service
public class ProductServiceImpl extends AbstractMapService implements ProductService {

	@Override
	public List<DomainObject>listAll(){
		return super.listAll();
	}
	
	@Override
	public Product getById(Integer id) {
		return (Product) super.getById(id);
	}

	@Override
	public Product saveOrUpdate(Product domainObject) {
		return (Product) super.saveOrUpdate(domainObject);
	}
	
	@Override
	public void delete(Integer id) {
		super.delete(id);
	};
	
	@Override
	protected void loadDomainObjects() {
		domainMap = new HashMap<>();
		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("Mouse");
		p1.setPrice(new BigDecimal(14.99));
		p1.setImageURL("http://example.com/product1");
		domainMap.put(1, p1);

		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("Screen");
		p2.setPrice(new BigDecimal(99.99));
		p2.setImageURL("http://example.com/product1");
		domainMap.put(2, p2);

	}

	
}
