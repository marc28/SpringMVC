package com.marc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.marc.domain.Product;
import com.marc.services.ProductService;

@Controller
public class ProductController {

	// bean needs injecting
	ProductService prodSer;

	@Autowired
	public void setProdSer(ProductService prodSer) {
		this.prodSer = prodSer;
	}

	/**
	 * Show all products
	 * 
	 */
	@RequestMapping("/product/list")
	public String getProducts(Model model) {
		// 'products' is referenced in products.html L19 &L28
		model.addAttribute("products", prodSer.listAll());
		return "product/list";
	}

	/**
	 * Get Product By id
	 */
	@RequestMapping("product/show/{id}")
	public String getProductById(@PathVariable Integer id, Model model) {
		model.addAttribute("product", prodSer.getById(id));
		return "product/show";
	}
	
	/**
	 * Editing a product
	 *
	 */
	@RequestMapping("product/edit/{id}")
	public String edit(@PathVariable Integer id,Model model){
		model.addAttribute("product",  prodSer.getById(id));
		return "product/productForm";
	}

	/**
	 * Adding a new Product
	 *
	 */
	@RequestMapping("product/new") // path
	public String addNewProduct(Model model) {
		// 'product' below is seen in productForm.html th:object="${product}" L22
		model.addAttribute("product", new Product());
		return "product/productForm"; // productForm refers to webpage name!!
	}

	// (value = "/product") refers to "th:action="@{/product}" in L22 productForm.html
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String saveOrUpdateProduct(Product product) {
		Product savedProduct = prodSer.saveOrUpdate(product);
		return "redirect:/product/show" + savedProduct.getId();
	}
	/**
	 * Deleting a Product
	 */
	@RequestMapping("product/delete/{id}")
	public String delete(@PathVariable Integer id){
		prodSer.delete(id);
		return "redirect:/product/list";
	}
	
}
