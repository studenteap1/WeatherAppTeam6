package com.askisi.myweatherapp.controller;
import Pojos.City;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Pojos.Favorite;
import java.util.List;
public class FavoriteRepo {

    private EntityManagerFactory entityManagerFactory;

    public FavoriteRepo() {
        entityManagerFactory = Persistence.createEntityManagerFactory("com.askisi_MyWeatherApp_jar_1.0-SNAPSHOTPU");
    }

    public void incrementOrInsertFavorite(City cityId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // Check if the city exists in favorites
        Query query = entityManager.createQuery("SELECT f FROM Favorite f WHERE f.cityId = :cityId");
        query.setParameter("cityId", cityId);
        List<Favorite> favorites = query.getResultList();

        if (favorites.isEmpty()) {
            // City does not exist, insert new favorite with searches count 1
            Favorite newFavorite = new Favorite();
            newFavorite.setCityId(cityId);
            newFavorite.setSearches(1);
            entityManager.persist(newFavorite);
        } else {
            // City exists, increment searches count
            Favorite existingFavorite = favorites.get(0);
            existingFavorite.setSearches(existingFavorite.getSearches() + 1);
            entityManager.merge(existingFavorite);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Object[]> getAllFavorites() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT c.name, f.searches FROM City c JOIN Favorite f ON c.id = f.cityId");
        List<Object[]> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    public void close() {
        entityManagerFactory.close();
    }
}
