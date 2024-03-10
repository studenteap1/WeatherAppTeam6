/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.askisi.myweatherapp.view;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import Pojos.City;
import com.askisi.myweatherapp.controller.CityRepo;
import com.askisi.myweatherapp.controller.WeatherRepo;
import java.util.Date;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author litos
 */
public class CityDataPanel extends JDialog{
    
    private JComboBox<String> cityComboBox;
    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;
    private JButton searchButton;
    private JTable weatherTable=new JTable();
    
    public CityDataPanel(JFrame parent) {
        super(parent, "Weather Search", true);
        CityRepo repo=new CityRepo();
        WeatherRepo wRepo=new WeatherRepo();
        setLocationRelativeTo(parent);
        setSize(600, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // City ComboBox
        JLabel cityLabel = new JLabel("City:");
        panel.add(cityLabel, gbc);

        cityComboBox = new JComboBox<>();
        List<City> cities = repo.getAllCities();
        for (City city : cities) {
            cityComboBox.addItem(city.getName());
        }
        gbc.gridx++;
        panel.add(cityComboBox, gbc);

        // Start Date Picker
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel startDateLabel = new JLabel("Start Date:");
        panel.add(startDateLabel, gbc);

        UtilDateModel startDateModel = new UtilDateModel();
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startDateModel);
        startDatePicker = new JDatePickerImpl(startDatePanel);
        gbc.gridx++;
        panel.add(startDatePicker, gbc);

        // End Date Picker
        gbc.gridx++;
        JLabel endDateLabel = new JLabel("End Date:");
        panel.add(endDateLabel, gbc);

        UtilDateModel endDateModel = new UtilDateModel();
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateModel);
        endDatePicker = new JDatePickerImpl(endDatePanel);
        gbc.gridx++;
        panel.add(endDatePicker, gbc);

        // Search Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 4;
        searchButton = new JButton("Search");
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                wRepo.searchWeatherData(cityComboBox.getSelectedItem().toString(),(Date) startDatePicker.getModel().getValue(),(Date) endDatePicker.getModel().getValue(),weatherTable);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });
        panel.add(searchButton, gbc);
        
       
        // Weather Table
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollPane = new JScrollPane(weatherTable);
        panel.add(scrollPane, gbc);
        getContentPane().add(panel);
    }
    
    
    
}
