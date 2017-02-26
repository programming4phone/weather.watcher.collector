package com.programming4phone.weather.watcher.collector.time;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DaylightTest {

	@Value("${local.server.port}")   
	int port;
	
	@Autowired
	private Daylight daylight;
	
	
	@Test
	public void test() {
		DaylightValues daylightValues = new DaylightValues()
				.setLatitude(35.165)
				.setLongitude(-80.789);
		
		daylightValues.setLocalObsDateTime("2017-02-15 04:54 AM");
		assertFalse(daylight.isDaylight(daylightValues));
		
		daylightValues.setLocalObsDateTime("2017-02-15 02:54 PM");
		assertTrue(daylight.isDaylight(daylightValues));
		
		daylightValues.setLocalObsDateTime("2017-02-15 06:54 PM");
		assertFalse(daylight.isDaylight(daylightValues));

	}

}
