package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.service.ServiceMainCommerce;

import javax.persistence.EntityManagerFactory;

public class ControllerComercios {
    private EntityManagerFactory entityManagerFactory;
    private ServiceMainCommerce service;

    public ControllerComercios(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.service = new ServiceMainCommerce(entityManagerFactory);
    }

    public void MoveToMainCommerce(){
        service.addCommercesInMainDB();
    }
}
