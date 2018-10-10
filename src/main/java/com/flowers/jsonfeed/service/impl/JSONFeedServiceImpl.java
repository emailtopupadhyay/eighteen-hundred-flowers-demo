package com.flowers.jsonfeed.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flowers.jsonfeed.exception.DataNotFoundException;
import com.flowers.jsonfeed.model.JSONDataResponse;
import com.flowers.jsonfeed.service.IJSONFeedService;

@Service
public class JSONFeedServiceImpl implements IJSONFeedService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONFeedServiceImpl.class);

	@Autowired
	RestTemplate restTemplete;

	/**
	 * This method will load data from server.
	 */
	@Override
	public List<JSONDataResponse> loadData() throws URISyntaxException {
		LOGGER.info("Starting  load data service...");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");

		ParameterizedTypeReference<List<JSONDataResponse>> typeRef = new ParameterizedTypeReference<List<JSONDataResponse>>() {
		};
		RequestEntity<JSONDataResponse> requestEntity = new RequestEntity<>(null, headers, HttpMethod.GET,
				new URI("http://localhost:9191/server/sampleData/"));

		ResponseEntity<List<JSONDataResponse>> exchange = restTemplete.exchange(requestEntity, typeRef);
		if (exchange.getBody() == null || exchange.getBody().isEmpty()) {
			throw new DataNotFoundException("Failed to load data....");
		}
		LOGGER.info("Completed  load data service without fail...");
		return exchange.getBody();
	}

}
