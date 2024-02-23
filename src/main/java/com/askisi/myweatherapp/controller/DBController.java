package com.askisi.myweatherapp.controller;

import com.askisi.myweatherapp.model.WeatherApi;

public interface DBController {
    void updateDB(WeatherApi weather);

    void searchFavourite();
}
