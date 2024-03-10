package com.askisi.myweatherapp.controller;

import Pojos.City;
import Pojos.Weather;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WeatherRepo {
    private EntityManagerFactory entityManagerFactory;

    public WeatherRepo() {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.askisi_MyWeatherApp_jar_1.0-SNAPSHOTPU");
    }

    public boolean dataExists(City city, Date date) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT w FROM Weather w WHERE w.cityId = :city AND w.weatherdate = :date");
        query.setParameter("city", city);
        query.setParameter("date", date);
        return !query.getResultList().isEmpty();
    }

    public void insertData(Weather weather) {
        if (!dataExists(weather.getCityId(), weather.getWeatherdate())) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(weather);
            entityManager.getTransaction().commit();
            System.out.println("Weather data inserted successfully.");
        } else {
            System.out.println("Weather data already exists in the database.");
        }
    }
    
    public void deleteData(City city, Date date) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("DELETE FROM Weather w WHERE w.cityId = :city AND w.weatherdate = :date");
    query.setParameter("city", city);
    query.setParameter("date", date);
    int deletedCount = query.executeUpdate();
    entityManager.getTransaction().commit();
    if (deletedCount > 0) {
        System.out.println("Weather data deleted successfully.");
    } else {
        JOptionPane.showMessageDialog(null, "No weather data found for the specified city and date.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
}   
    public void searchWeatherData(String name,Date startDate,Date endDate,JTable table) {
        String cityName = name;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query;
        if (startDate != null && endDate != null) {
            query = entityManager.createQuery("SELECT w FROM Weather w WHERE w.cityId.name = :cityName AND w.weatherdate BETWEEN :startDate AND :endDate");
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
        } else {
            query = entityManager.createQuery("SELECT w FROM Weather w WHERE w.cityId.name = :cityName");
        }
        query.setParameter("cityName", cityName);
        List<Weather> weatherData = query.getResultList();
       

        // Populate the weather data into the table
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Temperature");
        model.addColumn("Humidity");
        model.addColumn("Wind Speed");
        model.addColumn("UV Index");
        model.addColumn("Weather Description");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Weather weather : weatherData) {
            Object[] row = new Object[6];
            System.out.println(weather.getTemp());
            row[0] = dateFormat.format(weather.getWeatherdate());
            row[1] = weather.getTemp();
            row[2] = weather.getHumidity();
            row[3] = weather.getWindspeed();
            row[4] = weather.getUv();
            row[5] = weather.getWeatherdesk();
            model.addRow(row);
        }

        table.setModel(model);
    }

    public void close() {
        entityManagerFactory.close();
    }
}
