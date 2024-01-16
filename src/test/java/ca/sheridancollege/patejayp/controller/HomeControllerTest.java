package ca.sheridancollege.patejayp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.patejayp.beans.Capstone;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLoadingIndex() throws Exception {
		this.mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	@Test
	public void testLoadingIndexFailed() throws Exception {
		this.mockMvc.perform(get("/notFound"))
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void testLoadingInsertCapstone() throws Exception {
		this.mockMvc.perform(post("/insertCapstone")
		.flashAttr("capstone", new Capstone()))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}

	@Test
	public void testLoadingInsetCapstoneFailed() throws Exception {
		this.mockMvc.perform(post("/notFound")
		.flashAttr("capstone", new Capstone()))
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void testLoadingDeleteCapstone() throws Exception {
		this.mockMvc.perform(get("/deleteCapstone/{id}", 1))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}

	@Test
	public void testLoadingDeleteCapstoneFailed() throws Exception {
		this.mockMvc.perform(get("/notfound"))
		.andExpect(status().is4xxClientError());
	}

}
