package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.service.ServiceHome;

import javax.persistence.EntityManager;
import java.util.List;

public class ControllerHome {
    private EntityManager entityManager;
    private ServiceHome serviceHome;

    public ControllerHome(){
        EntityManager em = Factory.getFactory();
        this.serviceHome = new ServiceHome(em);
    }

//    public void BuscaLocal(String location){
//        //Acionar NearbySearch a partir da location recebida
          //Acionar AddCommerceListInDataBase passando a lista retornada de NearbySearch para adicionar as informações a tabela de transicao
//    }

}
