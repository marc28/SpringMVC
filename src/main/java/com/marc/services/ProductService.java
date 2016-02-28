package com.marc.services;

import com.marc.domain.Product;

/**
 * Note here how ProductService EXTENDS CRUDService<T> and the <T> is replaced by Product
 * 
 */
public interface ProductService extends CRUDService<Product>{

}
