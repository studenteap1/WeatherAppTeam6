package com.askisi.myweatherapp.repositories;

import com.google.gson.Gson;

import java.util.List;

public class WeatherApi {
    private String tempC;
    private String humidity;
    private String windspeedKmph;
    private String uvIndex;
    private String weatherDesc;
    private String city;

    // Constructor, getters, and setters

    public static class WeatherDesc {
        private String value;

        // Getter and setter for 'value'
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindspeedKmph() {
        return windspeedKmph;
    }

    public void setWindspeedKmph(String windspeedKmph) {
        this.windspeedKmph = windspeedKmph;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
}
