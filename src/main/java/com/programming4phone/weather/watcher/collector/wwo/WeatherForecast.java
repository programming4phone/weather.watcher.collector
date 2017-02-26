package com.programming4phone.weather.watcher.collector.wwo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherForecast {

	@JsonProperty("date")
	private String forecastDate;
	
	private String localObsDateTime;
	
	@JsonProperty("tempMaxC")
	private String temperatureMaxCentigrade;
	
	@JsonProperty("tempMinC")
	private String temperatureMinCentigrade;
	
	@JsonProperty("tempMaxF")
	private String temperatureMaxFahrenheit;
	
	@JsonProperty("tempMinF")
	private String temperatureMinFahrenheit;
	
	private String windspeedMiles;
	private String windspeedKmph;
	private String winddirection;
	private String winddir16Point;
	private String winddirDegree;
	private String weatherCode;
	private String precipMM;
	
	private List<WeatherIconUrl> weatherIconUrl;
	
	@JsonProperty("weatherDesc")
	private List<WeatherDescription> weatherDescription;

	public String getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(String forecastDate) {
		this.forecastDate = forecastDate;
	}

	public String getLocalObsDateTime() {
		return localObsDateTime;
	}

	public void setLocalObsDateTime(String localObsDateTime) {
		this.localObsDateTime = localObsDateTime;
	}

	public String getTemperatureMaxCentigrade() {
		return temperatureMaxCentigrade;
	}

	public void setTemperatureMaxCentigrade(String temperatureMaxCentigrade) {
		this.temperatureMaxCentigrade = temperatureMaxCentigrade;
	}

	public String getTemperatureMinCentigrade() {
		return temperatureMinCentigrade;
	}

	public void setTemperatureMinCentigrade(String temperatureMinCentigrade) {
		this.temperatureMinCentigrade = temperatureMinCentigrade;
	}

	public String getTemperatureMaxFahrenheit() {
		return temperatureMaxFahrenheit;
	}

	public void setTemperatureMaxFahrenheit(String temperatureMaxFahrenheit) {
		this.temperatureMaxFahrenheit = temperatureMaxFahrenheit;
	}

	public String getTemperatureMinFahrenheit() {
		return temperatureMinFahrenheit;
	}

	public void setTemperatureMinFahrenheit(String temperatureMinFahrenheit) {
		this.temperatureMinFahrenheit = temperatureMinFahrenheit;
	}

	public String getWindspeedMiles() {
		return windspeedMiles;
	}

	public void setWindspeedMiles(String windspeedMiles) {
		this.windspeedMiles = windspeedMiles;
	}

	public String getWindspeedKmph() {
		return windspeedKmph;
	}

	public void setWindspeedKmph(String windspeedKmph) {
		this.windspeedKmph = windspeedKmph;
	}

	public String getWinddirection() {
		return winddirection;
	}

	public void setWinddirection(String winddirection) {
		this.winddirection = winddirection;
	}

	public String getWinddir16Point() {
		return winddir16Point;
	}

	public void setWinddir16Point(String winddir16Point) {
		this.winddir16Point = winddir16Point;
	}

	public String getWinddirDegree() {
		return winddirDegree;
	}

	public void setWinddirDegree(String winddirDegree) {
		this.winddirDegree = winddirDegree;
	}

	public String getWeatherCode() {
		return weatherCode;
	}

	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
	}

	public String getPrecipMM() {
		return precipMM;
	}

	public void setPrecipMM(String precipMM) {
		this.precipMM = precipMM;
	}

	public List<WeatherIconUrl> getWeatherIconUrl() {
		return weatherIconUrl;
	}

	public void setWeatherIconUrl(List<WeatherIconUrl> weatherIconUrl) {
		this.weatherIconUrl = weatherIconUrl;
	}

	public List<WeatherDescription> getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(List<WeatherDescription> weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	@Override
	public String toString() {
		return "WeatherForecast [forecastDate=" + forecastDate + ", localObsDateTime=" + localObsDateTime
				+ ", temperatureMaxCentigrade=" + temperatureMaxCentigrade + ", temperatureMinCentigrade="
				+ temperatureMinCentigrade + ", temperatureMaxFahrenheit=" + temperatureMaxFahrenheit
				+ ", temperatureMinFahrenheit=" + temperatureMinFahrenheit + ", windspeedMiles=" + windspeedMiles
				+ ", windspeedKmph=" + windspeedKmph + ", winddirection=" + winddirection + ", winddir16Point="
				+ winddir16Point + ", winddirDegree=" + winddirDegree + ", weatherCode=" + weatherCode + ", precipMM="
				+ precipMM + ", weatherIconUrl=" + weatherIconUrl + ", weatherDescription=" + weatherDescription
				+ ", getForecastDate()=" + getForecastDate() + ", getLocalObsDateTime()=" + getLocalObsDateTime()
				+ ", getTemperatureMaxCentigrade()=" + getTemperatureMaxCentigrade()
				+ ", getTemperatureMinCentigrade()=" + getTemperatureMinCentigrade()
				+ ", getTemperatureMaxFahrenheit()=" + getTemperatureMaxFahrenheit()
				+ ", getTemperatureMinFahrenheit()=" + getTemperatureMinFahrenheit() + ", getWindspeedMiles()="
				+ getWindspeedMiles() + ", getWindspeedKmph()=" + getWindspeedKmph() + ", getWinddirection()="
				+ getWinddirection() + ", getWinddir16Point()=" + getWinddir16Point() + ", getWinddirDegree()="
				+ getWinddirDegree() + ", getWeatherCode()=" + getWeatherCode() + ", getPrecipMM()=" + getPrecipMM()
				+ ", getWeatherIconUrl()=" + getWeatherIconUrl() + ", getWeatherDescription()="
				+ getWeatherDescription() + "]";
	}
	


}
