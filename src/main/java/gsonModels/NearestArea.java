package gsonModels;

import java.util.List;

public class NearestArea {
    private List<AreaName> areaName;
    private List<Country> country;
    private String latitude;
    private String longitude;
    private String population;
    private List<Region> region;
    private List<WeatherUrl> weatherUrl;

    // Getters and setters

    public List<AreaName> getAreaName() {
        return areaName;
    }

    public void setAreaName(List<AreaName> areaName) {
        this.areaName = areaName;
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<Region> getRegion() {
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }

    public List<WeatherUrl> getWeatherUrl() {
        return weatherUrl;
    }

    public void setWeatherUrl(List<WeatherUrl> weatherUrl) {
        this.weatherUrl = weatherUrl;
    }
}
