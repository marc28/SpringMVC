package com.marc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.marc.controllers.ProductController;
import com.marc.domain.Product;
import com.marc.services.ProductService;

import scala.annotation.meta.param;

public class ProductControllerTest {

	// 1. We need the service and controller
	@Mock
	ProductService productService; // service
	@InjectMocks
	ProductController productController; // controller

	// 2. need this guy for .perform() .andExpect()
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); // initilizes controller and mocks
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testAllProducts() throws Exception {
		Product p1 = new Product();
		Product p2 = new Product();
		List<Product> productList = new ArrayList<>();
		productList.add(p1);
		productList.add(p2);

		// Here is the ProductService being called
		when(productService.listAll()).thenReturn((List) productList);

		mockMvc.perform(get("/product/list")).andExpect(status().isOk()).andExpect(view().name("product/list"))
				.andExpect(model().attribute("products", hasSize(2)));
	}

	@Test
	public void testGetById() throws Exception {
		Integer id = 1;

		when(productService.getById(id)).thenReturn(new Product());

		mockMvc.perform(get("/product/show/1")).andExpect(status().is(200)).andExpect(view().name("product/show"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
	}

	@Test
	public void testEditProduct() throws Exception {
		Integer id = 1;

		when(productService.getById(id)).thenReturn(new Product());

		mockMvc.perform(get("/product/edit/1")).andExpect(status().isOk()).andExpect(view().name("product/productForm"))
				.andExpect(model().attribute("product", instanceOf(Product.class)));
	}

	/**
	 * No productService called in this method, but can check to see its not
	 * called with verifyZeroInteractions(Service);
	 * @throws Exception 
	 */
	@Test
	public void testAddNewProduct() throws Exception {
		verifyZeroInteractions(productService);
		
		mockMvc.perform(get("/product/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("product/productForm"))
		.andExpect(model().attribute("product", instanceOf(Product.class)));
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception{
		//1. create the Product thats needed
		Integer id = 1;
		String description = "test decription"; 
		String imageURL = "imageURL";
		BigDecimal price = new BigDecimal("12.99");
		
		Product p = new Product();
		p.setId(id);
		p.setDescription(description);
		p.setImageURL(imageURL);
		p.setPrice(price);
		
		// 2. When()
		 when(productService.saveOrUpdate(Matchers.<Product>any())).thenReturn(p);
		
		 //3. This is post not get
		 mockMvc.perform(post("/product")
		 .param("id","1"))
		 .andExpect(status().is3xxRedirection())
		 .andExpect(view().name("redirect:/product/show/1"))
		 //Test the product thats being passed in
		 .andExpect(model().attribute("product", instanceOf(Product.class)))
		 .andExpect(model().attribute("product", hasProperty("id",is(id))));
	}

}


























