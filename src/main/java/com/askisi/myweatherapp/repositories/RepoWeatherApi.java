package com.askisi.myweatherapp.repositories;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import gsonModels.WeatherData;

public class RepoWeatherApi {

   public WeatherData getWeather(String city) {
        String apiUrl = "https://wttr.in/" + city + "?format=j1";

        // Create a new Gson instance
        Gson gson = new Gson();

        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

        // Create a request object
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        // Declare WeatherData object
        WeatherData weatherData = null;

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            // Check if the response is successful
            if (response.isSuccessful() && response.body() != null) {
                // Get the response body
                String responseBody = response.body().string();

                // Deserialize JSON response into WeatherData object
                weatherData = gson.fromJson(responseBody, WeatherData.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherData;
    }
}
