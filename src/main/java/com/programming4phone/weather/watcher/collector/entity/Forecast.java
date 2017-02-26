package com.programming4phone.weather.watcher.collector.entity;

public class Forecast {
	private String dayOfWeek; // 3 letter day of week MON, TUE, etc..
	//private String weatherCode; // identifies image
	private String weatherIconUrl;
	private String weatherDescription;
	private String lowTemp;
	private String highTemp;
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public Forecast setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
		return this;
	}
//	public String getWeatherCode() {
//		return weatherCode;
//	}
//	public Forecast setWeatherCode(String weatherCode) {
//		this.weatherCode = weatherCode;
//		return this;
//	}
	public String getWeatherIconUrl() {
		return weatherIconUrl;
	}
	public Forecast setWeatherIconUrl(String weatherIconUrl) {
		this.weatherIconUrl = weatherIconUrl;
		return this;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public Forecast setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
		return this;
	}
	public String getLowTemp() {
		return lowTemp;
	}
	public Forecast setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
		return this;
	}
	public String getHighTemp() {
		return highTemp;
	}
	public Forecast setHighTemp(String highTemp) {
		this.highTemp = highTemp;
		return this;
	}
	
	@Override
	public String toString() {
		return "Forecast [dayOfWeek=" + dayOfWeek + ", weatherIconUrl="
				+ weatherIconUrl + ", weatherDescription=" + weatherDescription 
				+ ", lowTemp=" + lowTemp + ", highTemp=" + highTemp + "]";
	}

}
