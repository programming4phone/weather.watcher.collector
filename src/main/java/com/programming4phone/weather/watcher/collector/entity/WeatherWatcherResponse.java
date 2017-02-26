package com.programming4phone.weather.watcher.collector.entity;

import java.util.List;

public class WeatherWatcherResponse {
	private RightNow rightnow;
	private List<Forecast> forecasts;
	
	public RightNow getRightnow() {
		return rightnow;
	}
	public void setRightnow(RightNow rightnow) {
		this.rightnow = rightnow;
	}
	public List<Forecast> getForecasts() {
		return forecasts;
	}
	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}
	@Override
	public String toString() {
		return "WeatherWatcherResponse [rightnow=" + rightnow + ", forecasts=" + forecasts + "]";
	}
}
