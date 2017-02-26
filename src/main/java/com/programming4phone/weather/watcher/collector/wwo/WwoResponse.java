package com.programming4phone.weather.watcher.collector.wwo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WwoResponse {
	
	@JsonProperty("data")
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "WwoResponse [data=" + data + "]";
	}
	
}
