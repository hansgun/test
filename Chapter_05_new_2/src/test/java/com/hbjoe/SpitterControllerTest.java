package com.hbjoe;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.hbjoe.data.SpitterRepository;
import com.hbjoe.web.SpitterController;

public class SpitterControllerTest {
	  @Test
	  public void shouldShowRegistration() throws Exception {
		  SpitterRepository mockRepository = mock(SpitterRepository.class);
		  SpitterController controller = new SpitterController(mockRepository);
		  MockMvc mocMvc = standaloneSetup(controller).build();
		  
		  mocMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	  }
	  
	  @Test
	  public void shouldProcessRegistration() throws Exception {
		  SpitterRepository mockRepository = mock(SpitterRepository.class);
		  Spitter unsaved = new Spitter("jbauer","24hours","Jack","Bauer");
		  Spitter saved = new Spitter(24L, "jbauer","24hours","Jack","Bauer");
		  when(mockRepository.save(unsaved)).thenReturn(saved);
		  
		  SpitterController controller = 
				  new SpitterController(mockRepository);
		  MockMvc mocMvc = standaloneSetup(controller).build();
		  
		  mocMvc.perform(post("/spitter/register")
				  .param("firstName", "Jack")
				  .param("lastName", "Bauer")
				  .param("username", "jbauer")
				  .param("password", "24hours"))
		  		  .andExpect(redirectedUrl("/spitter/jbauer"));
		  
		  verify(mockRepository, atLeastOnce()).save(unsaved);
	  }
}
