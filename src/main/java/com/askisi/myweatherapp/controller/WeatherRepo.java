package com.askisi.myweatherapp.controller;

import Pojos.City;
import Pojos.Weather;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import javax.swing.JOptionPane;

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

    public void close() {
        entityManagerFactory.close();
    }
}
