package com.programming4phone.weather.watcher.collector.entity;

public class RightNow {
	private String city;
	private String temp;
	//private String weatherCode; // identifies image
	private String weatherIconUrl;
	private String weatherDescription;
	//private Boolean isDaylight;
	
	public String getCity() {
		return city;
	}
	public RightNow setCity(String city) {
		this.city = city;
		return this;
	}
	public String getTemp() {
		return temp;
	}
	public RightNow setTemp(String temp) {
		this.temp = temp;
		return this;
	}
//	public String getWeatherCode() {
//		return weatherCode;
//	}
//	public RightNow setWeatherCode(String weatherCode) {
//		this.weatherCode = weatherCode;
//		return this;
//	}
	public String getWeatherIconUrl() {
		return weatherIconUrl;
	}
	public void setWeatherIconUrl(String weatherIconUrl) {
		this.weatherIconUrl = weatherIconUrl;
	}
	public String getWeatherDescription() {
		return weatherDescription;
	}
	public RightNow setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
		return this;
	}

//	public Boolean getIsDaylight() {
//		return isDaylight;
//	}
//	public void setIsDaylight(Boolean isDaylight) {
//		this.isDaylight = isDaylight;
//	}
	
	@Override
	public String toString() {
		return "RightNow [city=" + city + ", temp=" + temp + ", weatherIconUrl=" + weatherIconUrl
				+ ", weatherDescription=" + weatherDescription + "]";
	}
}
