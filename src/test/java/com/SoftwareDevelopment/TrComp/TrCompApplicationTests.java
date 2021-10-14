package com.SoftwareDevelopment.TrComp;

import static org.assertj.core.api.Assertions.assertThat;

import com.SoftwareDevelopment.TrComp.controllers.ReportController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TrCompApplicationTests {

	@Autowired
	private ReportController controller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
		this.mockMvc
				.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Меню")));
	}

}
