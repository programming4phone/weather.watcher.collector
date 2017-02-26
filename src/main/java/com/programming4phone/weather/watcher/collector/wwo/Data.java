package com.programming4phone.weather.watcher.collector.wwo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
	
	@JsonProperty("request")
	private List<RequestType> request;
	
	@JsonProperty("nearest_area")
	private List<NearestArea> nearestArea;

	@JsonProperty("current_condition")
	private List<CurrentCondition> currentCondition;
	
	@JsonProperty("weather")
	private List<WeatherForecast> forecasts;

	public List<RequestType> getRequest() {
		return request;
	}

	public void setRequest(List<RequestType> request) {
		this.request = request;
	}

	public List<NearestArea> getNearestArea() {
		return nearestArea;
	}

	public void setNearestArea(List<NearestArea> nearestArea) {
		this.nearestArea = nearestArea;
	}

	public List<CurrentCondition> getCurrentCondition() {
		return currentCondition;
	}

	public void setCurrentCondition(List<CurrentCondition> currentCondition) {
		this.currentCondition = currentCondition;
	}

	public List<WeatherForecast> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<WeatherForecast> forecasts) {
		this.forecasts = forecasts;
	}

	@Override
	public String toString() {
		return "Data [request=" + request + ", nearestArea=" + nearestArea + ", currentCondition=" + currentCondition
				+ ", forecasts=" + forecasts + "]";
	}
	

}
