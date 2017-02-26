# Weather Watcher Collector Web Service

The primary purpose of this web service is to provide weather information for the [Weather Watcher] (http://www.programming4phone.com/weather-watcher/index.html) Angular 2 application.

Information is supplied directly from [World Weather Online] (https://developer.worldweatheronline.com/api/default.aspx).

This code base adds to the World Weather Online API in the following ways:
+ CORS response headers are returned in the response
+ Weather information is cached in order to reduce the number of hits against the WWO API

## Using this web service

This web service is currently deployed on Heroku and can be accessed in a web browser using this link `https://weather-watcher-collector-0100.herokuapp.com/weather/{searchParam}` and substituting the `{searchParam}` value.

The value for `{searchParam}' can be a zip code, a city name, or a city/state/province/etc... combination.

For example, to get the Air Quality readings for zip code 28211, use this link [https://weather-watcher-collector-0100.herokuapp.com/weather/28211] (https://weather-watcher-collector-0100.herokuapp.com/weather/28211).

Results are cached for 1 hour for a particular searchParam value.

## Air Now API Access

In order to run this web service, you must obtain an World Weather Online [API Key] (https://developer.worldweatheronline.com/api/default.aspx) and use it to replace the value of the `wwo.api.key` property in the `application.properties` file.


## Development stack

This project was developed using Java 8, Spring Boot, Spring WS, and Ehcache.

## Build

Run `mvn clean install` to build the project and run the supplied unit tests. The build artifacts will be stored in the `target/` directory. 


