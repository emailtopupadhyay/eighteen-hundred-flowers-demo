package com.flowers.jsonfeed.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=JSONFeedController.class)
public class JSONFeedControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private JSONFeedController testController;

	@SuppressWarnings("static-access")
	@Before
	public void setUp() throws Exception {
		mockMvc = new MockMvcBuilders().standaloneSetup(testController).build();
	}

	@Test
	public void uniqueData_Test() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/uniqueData").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data", Matchers.hasSize(10)))
				.andExpect(jsonPath("$.numberOfuniqueUserid", Matchers.is(10)));

	}

	@Test
	public void update_Test() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/update/4").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.userId", Matchers.is("1")))
				.andExpect(jsonPath("$.id", Matchers.is("5")))
				.andExpect(jsonPath("$.title", Matchers.is("1800Flowers")))
				.andExpect(jsonPath("$.body", Matchers.is("1800Flowers")));

	}

}
