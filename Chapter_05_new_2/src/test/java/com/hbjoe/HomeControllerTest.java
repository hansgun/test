package com.hbjoe;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hbjoe.web.HomeController;

public class HomeControllerTest {
	@Test
	public void testHomePage_old() throws Exception {
		HomeController controller = new HomeController();
		assertEquals("home", controller.home());
	}
	
	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mockMvc =
				MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(view().name("home"));
	}
}
