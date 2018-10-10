package com.flowers.jsonfeed.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flowers.jsonfeed.model.JSONDataResponse;

@Service
@FunctionalInterface
public interface IJSONFeedService {

	public abstract List<JSONDataResponse> loadData() throws URISyntaxException;
}
