package com.programming4phone.weather.watcher.collector.wwo;

public class WeatherDescription {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "WeatherDescription [value=" + value + "]";
	}

}
