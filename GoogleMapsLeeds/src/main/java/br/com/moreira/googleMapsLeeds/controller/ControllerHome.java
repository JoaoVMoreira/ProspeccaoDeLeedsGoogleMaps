package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import br.com.moreira.googleMapsLeeds.service.ServiceHome;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

public class ControllerHome {
    private EntityManager entityManager;
    private ServiceHome serviceHome;

    public ControllerHome(){
        EntityManager em = Factory.getFactory();
        this.serviceHome = new ServiceHome(em);
    }

    public void BuscaLocal(String location) throws IOException, InterruptedException {
        List<String> commerceList = serviceHome.NearbySearch(location);
        for(String commerce : commerceList){
            System.out.println(commerce);
            ComerciosTransicaoModel commerceDetails = serviceHome.PlaceDetails(commerce);
            System.out.println(commerceDetails.getNome());
            serviceHome.AddCommerceListInDataBase(commerceDetails);
        }
    }

}
