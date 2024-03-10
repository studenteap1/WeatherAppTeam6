package com.askisi.myweatherapp.controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Pojos.City;
import java.util.List;

public class CityRepo {
    private EntityManagerFactory entityManagerFactory;

    public CityRepo() {
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
    
    public City getCityByName(String cityName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM City e WHERE e.name = :name");
        query.setParameter("name", cityName);
        return (City) query.getSingleResult(); // Assuming there's only one city with the given name
    }
    
    public List<City> getAllCities() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM City c");
        List<City> cities = query.getResultList();
        return cities;
    }
    
    public void close() {
        entityManagerFactory.close();
    }
}
