package com.programming4phone.weather.watcher.collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programming4phone.weather.watcher.collector.entity.WeatherWatcherResponse;

@RestController
public class WeatherController {

	@Autowired
	private WwoResponseProcessor wwoResponseProcessor;
	
	
	@RequestMapping(value="/weather/{searchParam}", 
			method = RequestMethod.GET,
			produces={"application/json"})
	public WeatherWatcherResponse getWeather(@PathVariable("searchParam") String searchParam){
		return wwoResponseProcessor.get(searchParam);
	}
}
