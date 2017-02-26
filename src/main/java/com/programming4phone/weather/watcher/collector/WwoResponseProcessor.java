package com.programming4phone.weather.watcher.collector;

import static java.util.concurrent.TimeUnit.HOURS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.cache.CacheManager;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.programming4phone.weather.watcher.collector.time.DaylightValues;
import com.programming4phone.weather.watcher.collector.entity.Forecast;
import com.programming4phone.weather.watcher.collector.entity.RightNow;
import com.programming4phone.weather.watcher.collector.entity.WeatherWatcherResponse;
import com.programming4phone.weather.watcher.collector.time.Daylight;
import com.programming4phone.weather.watcher.collector.wwo.WeatherForecast;
import com.programming4phone.weather.watcher.collector.wwo.WwoResponse;

@Component
@CacheDefaults(cacheName = "weather")
public class WwoResponseProcessor {
	/*
	 * Note: This class used to implement java.util.Function. However ehcache 
	 * would not work that way. 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(WwoResponseProcessor.class);
	private static final DateTimeFormatter INCOMING_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter SHORT_DAY_OF_WEEK = DateTimeFormatter.ofPattern("EEE");
	
	@Autowired
	private WwoInvoker wwoNowInvoker;
	
	@Autowired
	private Daylight daylight;
	
	@Value("${weather.image.url}")
    private String weatherImageUrl;
	
    @Component
    public static class CachingSetup implements JCacheManagerCustomizer {
      @Override
      public void customize(CacheManager cacheManager) {
        cacheManager.createCache("weather", new MutableConfiguration<>()
          .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(HOURS, 1)))
          .setStoreByValue(false)
          .setStatisticsEnabled(true));
      }
    }
	

	/**
	 * Use the search parameter value (zipcode, city, state, etc...) to invoke
	 * the World Weather Online webservice. The response from that web service
	 * is transformed into a WeatherWatcherResponse.
	 * @param searchParam String
	 * @return WeatherWatcherResponse
	 */
	@CacheResult
	public WeatherWatcherResponse get(String searchParam) {
		LOGGER.info("weather " + searchParam + " not found in cache. TimeStamp: {}", new Date());
		ResponseEntity<WwoResponse> wwoResponse = wwoNowInvoker.apply(searchParam);
		if(!wwoResponse.getStatusCode().equals(HttpStatus.OK)){
			String errorMessage = "WWO HttpStatus: " + wwoResponse.getStatusCode().value() + " for " + searchParam;
			LOGGER.error(errorMessage);
			throw new WwoException(errorMessage);
		}
		return buildWeatherWatcherResponse(wwoResponse.getBody());
	}
	
	/**
	 * Build the WeatherWatcherResponse from the WwoResponse.
	 * @param wwoResponse WwoResponse
	 * @return WeatherWatcherResponse
	 */
	private WeatherWatcherResponse buildWeatherWatcherResponse(WwoResponse wwoResponse){
		
		WeatherWatcherResponse response = new WeatherWatcherResponse();
		response.setRightnow(buildRightNow(wwoResponse));
		response.setForecasts(buildForecasts(wwoResponse));
		return response;
	}
	
	/**
	 * Build a RightNow object from the WwoResponse.
	 * @param wwoResponse WwoResponse
	 * @return RightNow
	 */
	private RightNow buildRightNow(WwoResponse wwoResponse){
		RightNow rightNow = new RightNow();
		
		wwoResponse.getData().getNearestArea().forEach(n->n.getAreaName().forEach(a->rightNow.setCity(a.getValue())));
		
		wwoResponse.getData().getCurrentCondition().forEach(c->{
			rightNow.setTemp(c.getTemperatureFahrenheit());
			rightNow.setWeatherIconUrl(buildWeatherImageUrl(c.getWeatherCode(),isDaylight(wwoResponse)));
			c.getWeatherDescription().forEach(d->rightNow.setWeatherDescription(d.getValue()));
		});
		return rightNow;
	}

	/**
	 * Build a list of Forecast objects from the WwoResponse.
	 * Forecast objects are built from WeatherForecast objects located in the WwoResponse.
	 * @param wwoResponse WwoResponse
	 * @return List of Forecast objects
	 */
	private List<Forecast> buildForecasts(WwoResponse wwoResponse){
		List<Forecast> forecasts = new ArrayList<Forecast>();
		wwoResponse.getData().getForecasts().forEach(f->forecasts.add(buildForecast(f)));
		return forecasts;
	}
	
	/**
	 * Build a single Forecast object from a WeatherForecast object.
	 * @param weatherForecast WeatherForecast
	 * @return Forecast
	 */
	private Forecast buildForecast(WeatherForecast weatherForecast){

		Forecast forecast = new Forecast()
				.setWeatherIconUrl(buildWeatherImageUrl(weatherForecast.getWeatherCode(),Boolean.TRUE))
				.setLowTemp(weatherForecast.getTemperatureMinFahrenheit())
				.setHighTemp(weatherForecast.getTemperatureMaxFahrenheit())
				.setDayOfWeek(LocalDate.parse(weatherForecast.getForecastDate(),INCOMING_DATE).format(SHORT_DAY_OF_WEEK));

		weatherForecast.getWeatherDescription().forEach(d->forecast.setWeatherDescription(d.getValue()));

		return forecast;
	}
	
	/**
	 * Determine if the observed current conditions occurred during the day. This
	 * information is ultimately used to determine whether to display a daylight weather
	 * icon or a night time weather icon.
	 * @param wwoResponse WwoResponse
	 * @return Boolean, true if the observed current conditions occurred during the day
	 */
	private Boolean isDaylight(WwoResponse wwoResponse){
		
		DaylightValues daylightValues = new DaylightValues();
		wwoResponse.getData().getCurrentCondition().forEach(c->daylightValues.setLocalObsDateTime(c.getLocalObsDateTime()));
		
		wwoResponse.getData().getNearestArea().forEach(n->{
			daylightValues.setLatitude(Double.valueOf(n.getLatitude()));
			daylightValues.setLongitude(Double.valueOf(n.getLongitude()));
		});
		
		return daylight.isDaylight(daylightValues);
	}
	
	/**
	 * Weather images come in 2 sets, one set for day time, and one set for night time.
	 * Forecast will always use the day time image. 
	 * The RightNow weather image needs to represent day or night based on the local observed time.
	 * @param weatherCode String
	 * @param isDaylight Boolean
	 * @return String containing the appropriate weather image url
	 */
	private String buildWeatherImageUrl(String weatherCode, Boolean isDaylight){
		return new StringBuilder()
			.append(weatherImageUrl)
			.append(isDaylight ? "day" : "night")
			.append(weatherCode)
			.append(".png").toString();
	}
}
