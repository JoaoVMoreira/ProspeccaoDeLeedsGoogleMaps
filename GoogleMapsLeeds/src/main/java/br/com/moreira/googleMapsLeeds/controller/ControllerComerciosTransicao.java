package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.service.ServiceTransitionCommerce;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ControllerComerciosTransicao {
    private ServiceTransitionCommerce serviceTransitionCommerce;
    private EntityManagerFactory entityManagerFactory;

    public ControllerComerciosTransicao(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.serviceTransitionCommerce = new ServiceTransitionCommerce(entityManagerFactory);
    }

    public List<ComerciosTransicaoDTO> listarComerciosTransicao(){
        List<ComerciosTransicaoDTO> comercios = serviceTransitionCommerce.listar();
        return comercios;
    }

    public void deleteItens(List<ComerciosTransicaoDTO> idList){
        for(ComerciosTransicaoDTO id : idList){
            serviceTransitionCommerce.DeleteCommercesFromDatabase(id.getId());
        }
    }
}
