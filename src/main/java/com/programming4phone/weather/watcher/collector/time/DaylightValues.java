package com.programming4phone.weather.watcher.collector.time;

public class DaylightValues {
	private String localObsDateTime;
	private Double latitude;
	private Double longitude;
	
	public String getLocalObsDateTime() {
		return localObsDateTime;
	}
	public DaylightValues setLocalObsDateTime(String localObsDateTime) {
		this.localObsDateTime = localObsDateTime;
		return this;
	}
	public Double getLatitude() {
		return latitude;
	}
	public DaylightValues setLatitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}
	public Double getLongitude() {
		return longitude;
	}
	public DaylightValues setLongitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}
	
	@Override
	public String toString() {
		return "DaylightValues [localObsDateTime=" + localObsDateTime + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
	
}
