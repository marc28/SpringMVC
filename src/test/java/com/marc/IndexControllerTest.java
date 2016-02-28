package com.marc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.marc.controllers.IndexController;

public class IndexControllerTest {

	private MockMvc mockMvc;
	private IndexController indexController;

	@Before
	public void setUp() throws Exception {
		indexController = new IndexController();
		mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
	}

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	@Test
	public void testHomePath() throws Exception {
		mockMvc.perform(get("/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	@Test
	public void testIncorrectPath() throws Exception {
		mockMvc.perform(get("/xyz"))
		.andExpect(status().isNotFound());
	}

}
