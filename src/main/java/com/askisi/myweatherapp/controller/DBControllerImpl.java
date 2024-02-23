package com.askisi.myweatherapp.controller;

import com.askisi.myweatherapp.model.WeatherApi;
import com.askisi.myweatherapp.model.WeatherDB;

public class DBControllerImpl implements DBController{
    WeatherDB weatherDB=new WeatherDB();
    @Override
    public void updateDB(WeatherApi weather) {
        if(!weather.getHumidity().equals(weatherDB.getTemp())){
            //ara kane update
        }



    }

    @Override
    public void searchFavourite() {
        
    }
}
