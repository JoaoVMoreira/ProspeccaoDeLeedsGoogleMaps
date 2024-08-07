package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import br.com.moreira.googleMapsLeeds.service.ServiceTransitionCommerce;
import br.com.moreira.googleMapsLeeds.service.ServiceMapsAPI;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ControllerMapsAPI {
    private ServiceMapsAPI serviceMapsAPI;
    private ServiceTransitionCommerce serviceTransitionCommerce;
    private EntityManagerFactory entityManagerFactory;

    public ControllerMapsAPI(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.serviceMapsAPI = new ServiceMapsAPI();
        this.serviceTransitionCommerce = new ServiceTransitionCommerce(entityManagerFactory);
    }

    public void BuscaLocal(String location, AtomicBoolean interromperThread) throws IOException, InterruptedException {
        List<String> commerceList = serviceMapsAPI.NearbySearch(location);
        for(String commerce : commerceList){
            if(interromperThread.get()){
                return;
            }
            ComerciosTransicaoModel commerceDetails = serviceMapsAPI.PlaceDetails(commerce);
            serviceTransitionCommerce.AddCommerceListInDataBase(commerceDetails);
        }
    }

}
