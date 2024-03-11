package gsonModels;


import gsonModels.Astronomy;
import gsonModels.HourlyInfo;
import java.util.List;

public class WeatherInfo {
    private List<Astronomy> astronomy;
    private String avgtempC;
    private String avgtempF;
    private String date;
    private List<HourlyInfo> hourly;

    // Getters and setters

    public List<Astronomy> getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(List<Astronomy> astronomy) {
        this.astronomy = astronomy;
    }

    public String getAvgtempC() {
        return avgtempC;
    }

    public void setAvgtempC(String avgtempC) {
        this.avgtempC = avgtempC;
    }

    public String getAvgtempF() {
        return avgtempF;
    }

    public void setAvgtempF(String avgtempF) {
        this.avgtempF = avgtempF;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<HourlyInfo> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyInfo> hourly) {
        this.hourly = hourly;
    }
    
}
