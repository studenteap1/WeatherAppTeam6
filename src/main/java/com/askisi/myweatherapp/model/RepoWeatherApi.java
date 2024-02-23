package com.askisi.myweatherapp.model;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RepoWeatherApi {

    public WeatherApi getWeather(String city) throws IOException {
        String apiUrl = "https://wttr.in/" + city + "?format=j1";

        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

        // Create a request object
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        // Execute the request and get the response
        try (Response response = client.newCall(request).execute()) {
            // Check if the response is successful
            if (response.isSuccessful() && response.body() != null) {
                // Get the response body
                String responseBody = response.body().string();

                // Extract the current_condition array
                JsonElement rootElement = new JsonParser().parse(responseBody);
                JsonArray currentConditionArray = rootElement.getAsJsonObject().getAsJsonArray("current_condition");

                if (currentConditionArray.size() > 0) {
                    // Get the first element of the array
                    JsonElement weatherElement = currentConditionArray.get(0);

                    JsonObject object = weatherElement.getAsJsonObject();

                    WeatherApi weather=new WeatherApi();

                    weather.setHumidity(object.get("humidity").getAsString());
                    weather.setTempC(object.get("temp_C").getAsString());
                    weather.setWindspeedKmph(object.get("windspeedKmph").getAsString());
                    weather.setUvIndex(object.get("uvIndex").getAsString());

                    if(object.get("weatherDesc").isJsonArray()){
                        // Get the 'weatherDesc' array
                        JsonArray weatherDescArray = object.get("weatherDesc").getAsJsonArray();
                        //Checking if array is not empty
                        if(weatherDescArray.size()>0){
                            // Access first element of array, get its 'value' property
                            String description = weatherDescArray.get(0).getAsJsonObject().get("value").getAsString();
                            // Set the description to weather
                            weather.setWeatherDesc(description);
                        }
                    }
                    return weather;
                }
                // Parse the JSON response and return a WeatherApi object
                //Implement parsing logic here based on your WeatherApi class structure
                //todo

                // Return the WeatherApi object
                return new WeatherApi();
            } else {
                // If the response is unsuccessful or the body is null, return null
                return null;
            }
        }
    }
}
