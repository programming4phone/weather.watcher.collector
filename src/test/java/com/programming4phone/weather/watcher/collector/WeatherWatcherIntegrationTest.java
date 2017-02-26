package com.programming4phone.weather.watcher.collector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.programming4phone.weather.watcher.collector.entity.WeatherWatcherResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeatherWatcherIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Value("${local.server.port}")   
	int port;

	/*
	 * This test is marked @Ignore because it hits the actual World Weather Online API.
	 * Uncomment to test.
	 *
	 */
	@Ignore
	@Test
	public void testCity() {
		
		
		ResponseEntity<WeatherWatcherResponse> entity;
		String city = "Chicago";
		entity = restTemplate
				.getForEntity("http://localhost:" + Integer.toString(port) + "/weather/" + city, WeatherWatcherResponse.class);

		assertTrue(entity.getStatusCode().equals(HttpStatus.OK));
		assertTrue(entity.getHeaders().containsKey("Access-Control-Allow-Origin"));
		WeatherWatcherResponse weatherWatcherResponse = entity.getBody();
		assertNotNull(weatherWatcherResponse);
		assertTrue(city.equalsIgnoreCase(weatherWatcherResponse.getRightnow().getCity()));
	}
	
	/*
	 * This test is marked @Ignore because it hits the actual World Weather Online API.
	 * Uncomment to test.
	 *
	 */
	@Ignore
	@Test
	public void testZipCode() {
		
		
		ResponseEntity<WeatherWatcherResponse> entity;
		String zipcode = "28211";
		String city = "Charlotte";
		entity = restTemplate
				.getForEntity("http://localhost:" + Integer.toString(port) + "/weather/" + zipcode, WeatherWatcherResponse.class);

		assertTrue(entity.getStatusCode().equals(HttpStatus.OK));
		assertTrue(entity.getHeaders().containsKey("Access-Control-Allow-Origin"));
		WeatherWatcherResponse weatherWatcherResponse = entity.getBody();
		assertNotNull(weatherWatcherResponse);
		assertTrue(city.equalsIgnoreCase(weatherWatcherResponse.getRightnow().getCity()));
	}
}
