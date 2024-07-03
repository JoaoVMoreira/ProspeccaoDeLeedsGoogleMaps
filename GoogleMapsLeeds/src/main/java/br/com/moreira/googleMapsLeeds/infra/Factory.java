package br.com.moreira.googleMapsLeeds.infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {
    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("google-maps-clients");

    public static EntityManager getFactory(){
        return FACTORY.createEntityManager();
    }
}
