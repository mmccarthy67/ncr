package com.ncr.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NcrRestControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation)).build();
	}
	
	@Test
	public void getAddressFromLatLng() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/address/{latitude}/{longitude}", 33.969601, -84.100033)).andExpect(status().isOk())
		.andDo(document("getAddressFromLatLng")).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertTrue("Address didn't contain '2651 Satellite Blvd, Duluth, GA 30096, USA'", result.getResponse().getContentAsString().contains("2651 Satellite Blvd, Duluth, GA 30096, USA"));
	}
	
	@Test
	public void getApiRequestFromCacheById() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/address/{id}", 1)).andExpect(status().isOk())
		.andDo(document("getApiRequestFromCacheById")).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void getApiRequestFromCache() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/address")).andExpect(status().isOk())
		.andDo(document("getApiRequestFromCache")).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
}