package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import br.com.moreira.googleMapsLeeds.service.ComerciosTransicaoService;
import br.com.moreira.googleMapsLeeds.service.ServiceMapsAPI;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ControllerHome{
    private ServiceMapsAPI serviceMapsAPI;
    private ComerciosTransicaoService comerciosTransicaoService;
    private EntityManagerFactory entityManagerFactory;

    public ControllerHome(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.serviceMapsAPI = new ServiceMapsAPI();
        this.comerciosTransicaoService = new ComerciosTransicaoService(entityManagerFactory);
    }

    public void BuscaLocal(String location, AtomicBoolean interromperThread) throws IOException, InterruptedException {
        List<String> commerceList = serviceMapsAPI.NearbySearch(location);
        for(String commerce : commerceList){
            if(interromperThread.get()){
                return;
            }
            ComerciosTransicaoModel commerceDetails = serviceMapsAPI.PlaceDetails(commerce);
            comerciosTransicaoService.AddCommerceListInDataBase(commerceDetails);
        }
    }

    public List<ComerciosTransicaoDTO> listarComerciosTransicao(){
        List<ComerciosTransicaoDTO> comercios = comerciosTransicaoService.listar();
        return comercios;
    }

    public void deleteItens(List<ComerciosTransicaoDTO> idList){
        for(ComerciosTransicaoDTO id : idList){
            comerciosTransicaoService.DeleteCommercesFromDatabase(id.getId());
        }
    }

}
