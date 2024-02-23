package com.askisi.myweatherapp.view;

import com.askisi.myweatherapp.model.WeatherApi;

import javax.swing.*;
import java.awt.*;

public class WeatherView extends JDialog {
    public WeatherView(JFrame parent,WeatherApi weather){
        super(parent, "Weather Dialog", true);
        setLocationRelativeTo(parent);
        this.setSize(400,400);
        JPanel panel=new JPanel(new GridLayout());
        JLabel tempLabel = new JLabel("Temperature: " + weather.getTempC() + "°C");
        JLabel humidityLabel = new JLabel("Humidity: " + weather.getHumidity() + "%");
        JLabel windspeedLabel = new JLabel("Windspeed: " + weather.getWindspeedKmph() + " km/h");
        JLabel uvIndexLabel = new JLabel("UV Index: " + weather.getUvIndex());
        JLabel weatherDescLabel = new JLabel("Weather Description: " + weather.getWeatherDesc());

        panel.add(tempLabel);
        panel.add(humidityLabel);
        panel.add(windspeedLabel);
        panel.add(uvIndexLabel);
        panel.add(weatherDescLabel);
        this.add(panel);

    }
}
