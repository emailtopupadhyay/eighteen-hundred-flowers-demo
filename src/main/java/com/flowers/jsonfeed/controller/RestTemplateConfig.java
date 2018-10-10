package com.flowers.jsonfeed.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.flowers.jsonfeed.model.Response;
import com.flowers.jsonfeed.service.impl.JSONFeedServiceImpl;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();

	}

	@Bean
	public Response response() {
		return new Response();
	}
	
	@Bean
	public JSONFeedServiceImpl getSrviceImpl() {
		return new JSONFeedServiceImpl();
	}

}
