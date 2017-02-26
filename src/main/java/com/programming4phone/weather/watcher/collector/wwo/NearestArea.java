package com.programming4phone.weather.watcher.collector.wwo;

import java.util.List;

public class NearestArea {
	private List<AreaName> areaName;
	private List<Country> country;
	private List<Region> region;
	private String latitude;
	private String longitude;
	public List<AreaName> getAreaName() {
		return areaName;
	}
	public void setAreaName(List<AreaName> areaName) {
		this.areaName = areaName;
	}
	public List<Country> getCountry() {
		return country;
	}
	public void setCountry(List<Country> country) {
		this.country = country;
	}
	public List<Region> getRegion() {
		return region;
	}
	public void setRegion(List<Region> region) {
		this.region = region;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "NearestArea [areaName=" + areaName + ", country=" + country + ", region=" + region + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	
}
