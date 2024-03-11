package com.askisi.myweatherapp.view;

import Pojos.City;
import Pojos.Weather;
import com.askisi.myweatherapp.controller.WeatherRepo;
import com.askisi.myweatherapp.repositories.WeatherApi;
import gsonModels.HourlyInfo;
import gsonModels.WeatherData;
import gsonModels.WeatherInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherView extends JDialog {
    public WeatherView(JFrame parent, WeatherData weather, City city) {

        super(parent, "Weather Dialog", true);
        setLocationRelativeTo(parent);
        this.setSize(800, 300);
        JPanel panel = new JPanel(new GridLayout(4, 8));
       

        // Labels for the column headers
        JLabel dateHeader = new JLabel("Date");
        JLabel tempHeader = new JLabel("Temperature");
        JLabel humidityHeader = new JLabel("Humidity");
        JLabel windspeedHeader = new JLabel("Windspeed");
        JLabel uvIndexHeader = new JLabel("UV Index");
        JLabel weatherDescHeader = new JLabel("Weather Description");

        // Add headers to the panel
        panel.add(dateHeader);
        panel.add(tempHeader);
        panel.add(humidityHeader);
        panel.add(windspeedHeader);
        panel.add(uvIndexHeader);
        panel.add(weatherDescHeader);
        panel.add(new JPanel());
        panel.add(new JPanel());

        // Add weather data to the panel
        for (int i = 0; i < weather.getWeather().size(); i++) {
            WeatherInfo weatherInfo=weather.getWeather().get(i);
            HourlyInfo hourlyData = weather.getWeather().get(i).getHourly().get(0); // Assuming hourly data is available
            
            
//            // Add labels for weather data
//            JLabel dateLabel = new JLabel(weatherInfo.getDate());
//            JLabel tempLabel = new JLabel(hourlyData.getTempC());
//            JLabel humidityLabel = new JLabel(hourlyData.getHumidity());
//            JLabel windspeedLabel = new JLabel(hourlyData.getWindspeedKmph());
//            JLabel uvIndexLabel = new JLabel(hourlyData.getUvIndex());
//            JLabel weatherDescLabel = new JLabel(hourlyData.getWeatherDesc().get(0).getValue());
//
//            // Add labels to the panel
//            panel.add(dateLabel);
//            panel.add(tempLabel);
//            panel.add(humidityLabel);
//            panel.add(windspeedLabel);
//            panel.add(uvIndexLabel);
//            panel.add(weatherDescLabel);

            // Add text fields for editing
            JLabel dateLabel = new JLabel(weatherInfo.getDate());
            JTextField tempTextField = new JTextField(hourlyData.getTempC());
            JTextField humidityTextField = new JTextField(hourlyData.getHumidity());
            JTextField windspeedTextField = new JTextField(hourlyData.getWindspeedKmph());
            JTextField uvIndexTextField = new JTextField(hourlyData.getUvIndex());
            JTextField weatherDescTextField = new JTextField(hourlyData.getWeatherDesc().get(0).getValue());

            // Add text fields to the panel
            panel.add(dateLabel);
            panel.add(tempTextField);
            panel.add(humidityTextField);
            panel.add(windspeedTextField);
            panel.add(uvIndexTextField);
            panel.add(weatherDescTextField);
            
           
            // Add buttons for each row
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the edited values from text fields
                    String editedTemp = tempTextField.getText();
                    String editedHumidity = humidityTextField.getText();
                    String editedWindspeed = windspeedTextField.getText();
                    String editedUvIndex = uvIndexTextField.getText();
                    String editedWeatherDesc = weatherDescTextField.getText();

                    WeatherRepo repo = new WeatherRepo();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Weather newWeather = new Weather();
                        newWeather.setCityId(city);
                        newWeather.setHumidity(editedHumidity);
                        newWeather.setTemp(editedTemp);
                        newWeather.setWindspeed(editedWindspeed);
                        newWeather.setUv(editedUvIndex);
                        newWeather.setWeatherdesk(editedWeatherDesc);
                        Date date = format.parse(weatherInfo.getDate());
                        newWeather.setWeatherdate(date);
                        repo.insertData(newWeather);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            JButton deleteButton = new JButton("Delete"); // Assuming you have a delete functionality
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the edited values from text fields
                    String editedTemp = tempTextField.getText();
                    String editedHumidity = humidityTextField.getText();
                    String editedWindspeed = windspeedTextField.getText();
                    String editedUvIndex = uvIndexTextField.getText();
                    String editedWeatherDesc = weatherDescTextField.getText();

                    WeatherRepo repo = new WeatherRepo();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Weather newWeather = new Weather();
                        newWeather.setCityId(city);
                        newWeather.setHumidity(editedHumidity);
                        newWeather.setTemp(editedTemp);
                        newWeather.setWindspeed(editedWindspeed);
                        newWeather.setUv(editedUvIndex);
                        newWeather.setWeatherdesk(editedWeatherDesc);
                        Date date = format.parse(weatherInfo.getDate());
                        newWeather.setWeatherdate(date);
                        repo.deleteData(city,date);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            // Add buttons to the panel
            panel.add(saveButton);
            panel.add(deleteButton);
        }

        this.add(panel);
    }
}

