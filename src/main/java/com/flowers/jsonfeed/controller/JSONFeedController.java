package com.flowers.jsonfeed.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowers.jsonfeed.model.JSONDataResponse;
import com.flowers.jsonfeed.model.Response;
import com.flowers.jsonfeed.service.impl.JSONFeedServiceImpl;

@RestController
@RequestMapping(value = "/data")
public class JSONFeedController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONFeedController.class);

	static Response response = new Response();

	@Autowired
	private JSONFeedServiceImpl feedService;

	@GetMapping(consumes = "application/json", produces = "application/json")
	public Response loadData() throws URISyntaxException {
		LOGGER.info("Calling load data service...");
		List<JSONDataResponse> jsonList = feedService.loadData();
		response.setMessage("Data successfully loaded...");
		response.setData(jsonList);
		LOGGER.info("Data loaded successfully....");
		return response;
		
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */

	@GetMapping(value = "/unique", consumes = "application/json", produces = "application/json")
	public Response uniqueData() throws IOException {
		LOGGER.info("Start of unique data....");

		Response responseData = new Response();
		if (Objects.nonNull(response) && !CollectionUtils.isEmpty(response.getData())) {
			List<JSONDataResponse> distinctElements = response.getData().stream()
					.filter(distinctByKey(JSONDataResponse::getUserId)).collect(Collectors.toList());
			responseData.setNumberOfuniqueUserid(distinctElements.size());
			responseData.setData(distinctElements);
			responseData.setMessage(
					"No of total unique user id are : " + distinctElements.size() + " successfully retrived....");
		} else {
			responseData.setMessage("Failed to load data....");
			// throw new DataNotFoundException("Data not loaded yet, please load
			// data first....")
		}
		LOGGER.info("End of unique data....");
		return responseData;
	}

	/**
	 * 
	 * @param keyExtractor
	 * @return
	 */
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		LOGGER.info("Start of distinctByKey ....");
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		LOGGER.info("End of distinctByKey ....");
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	@PutMapping(value = "update/{index}", consumes = "application/json", produces = "application/json")
	public JSONDataResponse update(@PathVariable int index, @RequestBody JSONDataRequest request) {
		LOGGER.info("Start of update  data....");
		if (Objects.nonNull(response) && !CollectionUtils.isEmpty(response.getData()) && index >= 0
				&& response.getData().size() <= 100) {
			List<JSONDataResponse> dataList = response.getData();
			JSONDataResponse jsonDataResponse = dataList.get(index);
			jsonDataResponse.setBody(request.getBody());
			jsonDataResponse.setTitle(request.getTitle());
			jsonDataResponse.setMessage("Title and body are successfully updated for index " + index);
			response.getData().set(index, jsonDataResponse);
			LOGGER.info("End of update data....");
			return jsonDataResponse;
		}
		return new JSONDataResponse();
	}
	
	/**
	 * @return
	 */
	@GetMapping(value="/updatedData",consumes = "application/json", produces = "application/json")
	public Response getUpdatedData(){
		LOGGER.info("Start of getUpdatedData");
		response.setMessage("Data successfully loaded...");
		LOGGER.info("End of getUpdatedData");
		return response;
		
	}

}
