package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import br.com.moreira.googleMapsLeeds.service.ComerciosTransicaoService;
import br.com.moreira.googleMapsLeeds.service.ServiceHome;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;

public class ControllerHome {
    private ServiceHome serviceHome;
    private ComerciosTransicaoService comerciosTransicaoService;
    private EntityManagerFactory entityManagerFactory;

    public ControllerHome(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.serviceHome = new ServiceHome();
        this.comerciosTransicaoService = new ComerciosTransicaoService(entityManagerFactory);
    }

    public void BuscaLocal(String location) throws IOException, InterruptedException {
        System.out.println("Buscar local iniciado");
        List<String> commerceList = serviceHome.NearbySearch(location);
        for(String commerce : commerceList){
            ComerciosTransicaoModel commerceDetails = serviceHome.PlaceDetails(commerce);
            comerciosTransicaoService.AddCommerceListInDataBase(commerceDetails);
        }
    }

    public List<ComerciosTransicaoDTO> listarComerciosTransicao(){
        List<ComerciosTransicaoDTO> comercios = comerciosTransicaoService.listar();
        return comercios;
    }

}
