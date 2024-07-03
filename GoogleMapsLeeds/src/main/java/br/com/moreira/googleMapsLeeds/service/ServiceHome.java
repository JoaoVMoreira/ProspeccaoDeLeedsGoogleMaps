package br.com.moreira.googleMapsLeeds.service;

import javax.persistence.EntityManager;
import java.util.List;

public class ServiceHome {
    private EntityManager entityManager;

    public ServiceHome(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<String> NearbySearch(String location){
        //consumo da API retornando uma lista de place_id
    }

    public void AddCommerceListInDataBase(List<String> comercios_transicao){
        //adicionar lista recebida ao DB de transição
    }

    //RemoveCommerceFromDataBase
    //MoveCommercesToOficialDataBase


}
