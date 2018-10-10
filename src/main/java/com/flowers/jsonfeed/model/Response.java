package com.flowers.jsonfeed.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	
	private String message;
	private int numberOfuniqueUserid;
	private List<JSONDataResponse> data;
	private JSONDataResponse updateResponse;
	
	public int getNumberOfuniqueUserid() {
		return numberOfuniqueUserid;
	}
	public void setNumberOfuniqueUserid(int numberOfuniqueUserid) {
		this.numberOfuniqueUserid = numberOfuniqueUserid;
	}
	public List<JSONDataResponse> getData() {
		return data;
	}
	public void setData(List<JSONDataResponse> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public JSONDataResponse getUpdateResponse() {
		return updateResponse;
	}
	public void setUpdateResponse(JSONDataResponse updateResponse) {
		this.updateResponse = updateResponse;
	}
	
	
}
