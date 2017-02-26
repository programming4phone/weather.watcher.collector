package com.programming4phone.weather.watcher.collector.wwo;

public class RequestType {
	private String type;
	private String query;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	@Override
	public String toString() {
		return "RequestType [type=" + type + ", query=" + query + "]";
	}
	
}
