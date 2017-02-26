package com.programming4phone.weather.watcher.collector.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;
 
/**
 * 
 * Based on algorithm at http://williams.best.vwh.net/sunrise_sunset_algorithm.htm
 * 
 */
@Component
 public class Daylight {
 
    private static final double OFFICIAL_ZENITH = 90.833333;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
    
    /**
     * Determine if the local observed time is during daylight hours. The calculation uses the latitude and 
     * longitude where the weather condition is observed.
     * @param values DaylightValues (time, latitude, longitude)
     * @return Boolean, true if observation takes place during daylight
     */
    public Boolean isDaylight(DaylightValues values){
    	Boolean isDaylight;

		try {
			TimeZone tz = findTimeZone(values.getLongitude());
			
			DATE_FORMAT.setTimeZone(tz);
			Date observationDate = DATE_FORMAT.parse(values.getLocalObsDateTime());
			
			Date sunrise = getSunrise(values.getLatitude(), values.getLongitude(), observationDate, tz);
			Date sunset = getSunset(values.getLatitude(), values.getLongitude(), observationDate, tz);
			
			isDaylight = observationDate.after(sunrise) && observationDate.before(sunset);
		} 
		catch (ParseException e) {
			isDaylight = Boolean.TRUE;
		}

		return isDaylight;
    }
    
    /**
     * Determine the time zone based on the decimal longitude value.
     * Based on calculation supplied by:<br/>
     * http://www.cs4fn.org/mobile/owntimezone.php
     * @param longitude double
     * @return TimeZone
     */
	private TimeZone findTimeZone(double longitude){
		return TimeZone.getTimeZone("GMT" + Long.toString(Math.round(longitude/0.004167/60.0/60.0)));
	}
     
    /**
      * Gets the sunset for today at a particular location with a particular zenith.
      * <br/>Based on algorithm at http://williams.best.vwh.net/sunrise_sunset_algorithm.htm
      * 
      * @param latitude
      * @param longitude
      * @param zenith
      * @return GMT date of the rise.  If the sun does not rise, returns null
      */
     public Date getSunset(double latitude, double longitude, Date date, TimeZone tz) {
         return this.getSunset(latitude, longitude, date, OFFICIAL_ZENITH, tz);
     }
     
    /**
      * Gets the time of a sunset for a particular location.
      * <br/>Based on algorithm at http://williams.best.vwh.net/sunrise_sunset_algorithm.htm
      * 
     * @param latitude location of sunset
      * @param longitude location of sunset
      * @param date date of sunset
      * @return GMT date of the sunset.  If the sun does not set, returns null
      */
     public Date getSunset(double latitude, double longitude, Date date, double zenith, TimeZone tz) {
         double PiOver180 = Math.PI / 180;
         double One80OverPi = 180 / Math.PI;
         // Get the day of the year
         Calendar calendar = Calendar.getInstance();
         calendar.clear();
         calendar.setTimeZone(tz);
         calendar.setTime(date);
         int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
         int year = calendar.get(Calendar.YEAR);
 
        // convert the longitude to hour value 
        double longitudeHour = longitude / 15;
         double settingTime = dayOfYear + ((18 - longitudeHour) / 24);
 
        // calculate the sun's mean anomaly
         double sunMeanAnomaly = (0.9856 * settingTime) - 3.289;
 
        // calculate the sun's true longitude
         double sunTrueLongitude = (sunMeanAnomaly 
                + (1.916 * Math.sin(sunMeanAnomaly * PiOver180))
                 + (0.020 * Math.sin(sunMeanAnomaly * 2 * PiOver180))
                 + 282.634);
         if (sunTrueLongitude < 0.0) {
             sunTrueLongitude += 360;
         }
         if (sunTrueLongitude > 360) {
             sunTrueLongitude -= 360;
         }
 
        // calculate the Sun's right ascension
         double sunRightAscension = One80OverPi * Math.atan(0.91764 * Math.tan(PiOver180 * sunTrueLongitude));
 
        // right ascension value needs to be in the same quadrant as the sun's true longitude
         double sunTrueLongitudeQuadrant = (Math.floor(sunTrueLongitude/90) * 90);
         double sunRightAscensionQuadrant = (Math.floor(sunRightAscension/90) * 90);
         sunRightAscension = sunRightAscension + (sunTrueLongitudeQuadrant - sunRightAscensionQuadrant);
 
        // convert right ascension value to hours
         sunRightAscension = sunRightAscension / 15;
 
        // calculate the sun's declination
         double sinDeclination = (0.39782 * (Math.sin(PiOver180 * sunTrueLongitude)));
         double cosDeclination = Math.cos(Math.asin(sinDeclination));
 
        // calculate the sun's local hour angle - use zenith as 90 50' cos(zenith) = -0.01454
         double cosHour = ((-0.01454) - (sinDeclination * (Math.sin(PiOver180 * latitude)))) / (cosDeclination * Math.cos(PiOver180 * latitude));
         if (cosHour < -1) {
             // sun never sets
             return null;
         }
 
        // finish calculating local hour angle and convert to hours
         double localHourAngle = (One80OverPi * Math.acos(cosHour)) / 15;
 
        // calculate the local mean time of setting
         double localMeanTime = localHourAngle + sunRightAscension - (0.06571 * settingTime) - 6.622;
 
        // adjust back to UTC
         double utcTime = localMeanTime - longitudeHour;
         utcTime = (utcTime < 0) ? utcTime + 24 : utcTime;
         //utcTime = (utcTime > 24) ? utcTime - 24 : utcTime;
         
        // Get the GMT time
         int returnTime = (int) Math.floor(utcTime * 60 * 60 * 1000);
         
        calendar.clear();
         calendar.setTimeZone(tz);
         calendar.add(Calendar.MILLISECOND, returnTime);
         calendar.add(Calendar.MILLISECOND, tz.getRawOffset());
         calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
         calendar.set(Calendar.YEAR, year);
 
        return calendar.getTime();
     }
    
     /**
      *  Gets the sunrise for a particular date at a particular location.
      * <br/>Based on algorithm at http://williams.best.vwh.net/sunrise_sunset_algorithm.htm
      * @param latitude
      * @param longitude
      * @param date
      * @param tz
      * @return  GMT date of the rise.  If the sun does not rise, returns null
      */
    public Date getSunrise(double latitude, double longitude, Date date, TimeZone tz) {
         Date gmtDate =  this.getSunrise(latitude, longitude, date, OFFICIAL_ZENITH, tz);
         return new Date(gmtDate.getTime());
     }
     
    /**
      * Gets the sunrise for a particular date at a particular location.
      * <br/>Based on algorithm at http://williams.best.vwh.net/sunrise_sunset_algorithm.htm
      * 
      * @param latitude
      * @param longitude
      * @param date
      * @param zenith
      * @return GMT date of the rise.  If the sun does not rise, returns null
      */
     public Date getSunrise(double latitude, double longitude, Date date, double zenith, TimeZone tz) {
         double PiOver180 = Math.PI / 180;
         double One80OverPi = 180 / Math.PI;
         // Get the day of the year
         Calendar calendar = Calendar.getInstance();
         calendar.clear();
         calendar.setTime(date);
         calendar.setTimeZone(tz);
         
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
         int year = calendar.get(Calendar.YEAR);
 
        // convert the longitude to hour value 
        double longitudeHour = longitude / 15;
         double risingTime = dayOfYear + ((6 - longitudeHour) / 24);
 
        // calculate the sun's mean anomaly
         double sunMeanAnomaly = (0.9856 * risingTime) - 3.289;
 
        // calculate the sun's true longitude
         
        double sunTrueLongitude = (sunMeanAnomaly 
                + (1.916 * Math.sin(sunMeanAnomaly * PiOver180))
                 + (0.020 * Math.sin(sunMeanAnomaly * 2 * PiOver180))
                 + 282.634);
         if (sunTrueLongitude < 0.0) {
             sunTrueLongitude += 360;
         }
         if (sunTrueLongitude > 360) {
             sunTrueLongitude -= 360;
         }
 
        // calculate the Sun's right ascension
         double sunRightAscension = One80OverPi * Math.atan(0.91764 * Math.tan(PiOver180 * sunTrueLongitude));
 
        // right ascension value needs to be in the same quadrant as the sun's true longitude
         double sunTrueLongitudeQuadrant = (Math.floor(sunTrueLongitude/90) * 90);
         double sunRightAscensionQuadrant = (Math.floor(sunRightAscension/90) * 90);
         sunRightAscension = sunRightAscension + (sunTrueLongitudeQuadrant - sunRightAscensionQuadrant);
 
        // convert right ascension value to hours
         sunRightAscension = sunRightAscension / 15;
 
        // calculate the sun's declination
         double sinDeclination = (0.39782 * (Math.sin(PiOver180 * sunTrueLongitude)));
         double cosDeclination = Math.cos(Math.asin(sinDeclination));
 
        // calculate the sun's local hour angle - use zenith as 90 50' cos(zenith) = -0.01454
         double cosHour = ((-0.01454) - (sinDeclination * (Math.sin(PiOver180 * latitude)))) / (cosDeclination * Math.cos(PiOver180 * latitude));
         if (cosHour > 1) {
             // sun never rises
             return null;
         }
 
        // finish calculating local hour angle and convert to hours
         double localHourAngle = (360 - (One80OverPi * Math.acos(cosHour))) / 15;
 
        // calculate the local mean time of rising
         double localMeanTime = localHourAngle + sunRightAscension - (0.06571 * risingTime) - 6.622;
         //localMeanTime = (localMeanTime < 0) ? localMeanTime + 24 : localMeanTime;
         //localMeanTime = (localMeanTime > 24) ? localMeanTime - 24 : localMeanTime;
 
        // adjust back to UTC
         double utcTime = localMeanTime - longitudeHour;
         utcTime = (utcTime < 0) ? utcTime + 24 : utcTime;
         utcTime = (utcTime > 24) ? utcTime - 24 : utcTime;
         
        // Get the GMT time
         int returnTime = (int) Math.floor(utcTime * 60 * 60 * 1000);
         
        calendar.clear();
         calendar.setTimeZone(tz);
         calendar.add(Calendar.MILLISECOND, returnTime);
         calendar.add(Calendar.MILLISECOND, tz.getRawOffset());
         calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
         calendar.set(Calendar.YEAR, year);
 
        return calendar.getTime();
     }
     
}
