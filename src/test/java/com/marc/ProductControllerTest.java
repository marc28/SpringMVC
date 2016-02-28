package com.marc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import com.marc.controllers.ProductController;
import com.marc.domain.Product;
import com.marc.services.ProductService;

public class ProductControllerTest {
	
	//1. We need the service and controller
	@Mock
	ProductService productService; //service
	@InjectMocks
	ProductController productController; //controller
	
	//2. need this guy for .perform() .andExpect()
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); //initilizes controller and mocks
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testAllProducts() throws Exception {
		Product p1 = new Product();
		Product p2 = new Product();
		List<Product> productList = new ArrayList<>();
		productList.add(p1);
		productList.add(p2);
		
		//Here is the ProductService being called
		when(productService.listAll()).thenReturn((List)productList);
		
		mockMvc.perform(get("/product/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("product/list"))
		.andExpect(model().attribute("products", hasSize(2)));
	}

}
