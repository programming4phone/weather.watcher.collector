package com.programming4phone.weather.watcher.collector.wwo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentCondition {

	@JsonProperty("observation_time")
	private String observationTime;
	
	private String localObsDateTime;
	
	@JsonProperty("temp_C")
	private String temperatureCentigrade;
	
	@JsonProperty("temp_F")
	private String temperatureFahrenheit;
	
	private String weatherCode;
	
	private List<WeatherIconUrl> weatherIconUrl;
	
	@JsonProperty("weatherDesc")
	private List<WeatherDescription> weatherDescription;
	
	private String windspeedMiles;
	private String windspeedKmph;
	private String winddirDegree;
	private String winddir16Point;
	private String precipMM;
	private String humidity;
	private String visibility;
	private String pressure;
	private String cloudcover;
	public String getObservationTime() {
		return observationTime;
	}
	public void setObservationTime(String observationTime) {
		this.observationTime = observationTime;
	}
	public String getLocalObsDateTime() {
		return localObsDateTime;
	}
	public void setLocalObsDateTime(String localObsDateTime) {
		this.localObsDateTime = localObsDateTime;
	}
	public String getTemperatureCentigrade() {
		return temperatureCentigrade;
	}
	public void setTemperatureCentigrade(String temperatureCentigrade) {
		this.temperatureCentigrade = temperatureCentigrade;
	}
	public String getTemperatureFahrenheit() {
		return temperatureFahrenheit;
	}
	public void setTemperatureFahrenheit(String temperatureFahrenheit) {
		this.temperatureFahrenheit = temperatureFahrenheit;
	}
	public String getWeatherCode() {
		return weatherCode;
	}
	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
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
	public String getWinddirDegree() {
		return winddirDegree;
	}
	public void setWinddirDegree(String winddirDegree) {
		this.winddirDegree = winddirDegree;
	}
	public String getWinddir16Point() {
		return winddir16Point;
	}
	public void setWinddir16Point(String winddir16Point) {
		this.winddir16Point = winddir16Point;
	}
	public String getPrecipMM() {
		return precipMM;
	}
	public void setPrecipMM(String precipMM) {
		this.precipMM = precipMM;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getCloudcover() {
		return cloudcover;
	}
	public void setCloudcover(String cloudcover) {
		this.cloudcover = cloudcover;
	}
	
	@Override
	public String toString() {
		return "CurrentCondition [observationTime=" + observationTime + ", localObsDateTime=" + localObsDateTime
				+ ", temperatureCentigrade=" + temperatureCentigrade + ", temperatureFahrenheit="
				+ temperatureFahrenheit + ", weatherCode=" + weatherCode + ", weatherIconUrl=" + weatherIconUrl
				+ ", weatherDescription=" + weatherDescription + ", windspeedMiles=" + windspeedMiles
				+ ", windspeedKmph=" + windspeedKmph + ", winddirDegree=" + winddirDegree + ", winddir16Point="
				+ winddir16Point + ", precipMM=" + precipMM + ", humidity=" + humidity + ", visibility=" + visibility
				+ ", pressure=" + pressure + ", cloudcover=" + cloudcover + ", getObservationTime()="
				+ getObservationTime() + ", getLocalObsDateTime()=" + getLocalObsDateTime()
				+ ", getTemperatureCentigrade()=" + getTemperatureCentigrade() + ", getTemperatureFahrenheit()="
				+ getTemperatureFahrenheit() + ", getWeatherCode()=" + getWeatherCode() + ", getWeatherIconUrl()="
				+ getWeatherIconUrl() + ", getWeatherDescription()=" + getWeatherDescription()
				+ ", getWindspeedMiles()=" + getWindspeedMiles() + ", getWindspeedKmph()=" + getWindspeedKmph()
				+ ", getWinddirDegree()=" + getWinddirDegree() + ", getWinddir16Point()=" + getWinddir16Point()
				+ ", getPrecipMM()=" + getPrecipMM() + ", getHumidity()=" + getHumidity() + ", getVisibility()="
				+ getVisibility() + ", getPressure()=" + getPressure() + ", getCloudcover()=" + getCloudcover()
				+ "]";
	}

}
