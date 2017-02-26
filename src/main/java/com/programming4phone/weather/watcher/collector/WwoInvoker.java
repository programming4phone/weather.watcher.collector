package com.programming4phone.weather.watcher.collector;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.programming4phone.weather.watcher.collector.wwo.WwoResponse;

@Component
public class WwoInvoker implements Function <String,ResponseEntity<WwoResponse>>{

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${wwo.api.key}")
    private String key;
	
	@Override
	public ResponseEntity<WwoResponse> apply(String searchParam) {
		return restTemplate.exchange(
				buildWwoUrl(searchParam),
		        HttpMethod.GET, 
		        null, 
		        WwoResponse.class);
	}
	

	private String buildWwoUrl(String searchParam){
		return new StringBuilder()
				.append("http://api.worldweatheronline.com/free/v1/weather.ashx?q=")
				.append(searchParam)
				.append("&format=json&num_of_days=5&includeLocation=yes&extra=localObsTime&key=")
				.append(key)
				.toString();
	}

}
