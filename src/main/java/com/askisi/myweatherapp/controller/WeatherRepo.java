package com.askisi.myweatherapp.controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import pojos.City;

public class WeatherRepo {
    private EntityManagerFactory entityManagerFactory;

    public WeatherRepo() {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.askisi_MyWeatherApp_jar_1.0-SNAPSHOTPU");
    }

    public boolean dataExists(String data) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM City e WHERE e.name = :data");
        query.setParameter("data", data);
        return !query.getResultList().isEmpty();
    }

    public void insertData(String data) {
        if (!dataExists(data)) {
            City entity = new City();
            entity.setName(data);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            System.out.println("Data inserted successfully.");
        } else {
            System.out.println("Data already exists in the database.");
        }
    }

    public void close() {
        entityManagerFactory.close();
    }
}
