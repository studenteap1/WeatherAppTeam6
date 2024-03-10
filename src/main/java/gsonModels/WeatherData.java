/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gsonModels;

import java.util.List;

/**
 *
 * @author litos
 */
public class WeatherData {
    private List<CurrentCondition> current_condition;
    private List<NearestArea> nearest_area;
    private List<WeatherRequest> request;
    private List<WeatherInfo> weather;

    // Getters and setters

    public List<CurrentCondition> getCurrentCondition() {
        return current_condition;
    }

    public void setCurrentCondition(List<CurrentCondition> currentCondition) {
        this.current_condition = currentCondition;
    }

    public List<NearestArea> getNearest_area() {
        return nearest_area;
    }

    public void setNearest_area(List<NearestArea> nearest_area) {
        this.nearest_area = nearest_area;
    }

    public List<WeatherRequest> getRequest() {
        return request;
    }

    public void setRequest(List<WeatherRequest> request) {
        this.request = request;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }
}
