package com.askisi.myweatherapp.view;

import com.askisi.myweatherapp.model.RepoWeatherApi;
import com.askisi.myweatherapp.model.WeatherApi;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JFrame {
    public MainPanel() {

        this.setTitle("WEATHER APP");
        RepoWeatherApi repoWeatherApi = new RepoWeatherApi();


        // Create a JPanel for the top section
        JPanel topPanel = new JPanel(new BorderLayout());


        // Create a JTextField and a JButton
        JTextField textField = new JTextField();
        JButton button = new JButton("Search");
        JPanel gridPanel = new JPanel(new GridLayout(5, 1));
        button.addActionListener(e -> {
            try {
                WeatherView weatherView =new WeatherView(this,repoWeatherApi.getWeather(textField.getText().trim().strip()));
                weatherView.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add the JTextField and JButton to the top panel
        topPanel.add(textField, BorderLayout.CENTER);
        topPanel.add(button, BorderLayout.EAST);

        // Create a JPanel for the grid layout


        // Add buttons to the grid panel
        for (int i = 1; i <= 4; i++) {
            JButton gridButton = new JButton("Button " + i);
            gridPanel.add(gridButton);
        }

        // Add the top panel and the grid panel to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);

        // Set frame properties
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
