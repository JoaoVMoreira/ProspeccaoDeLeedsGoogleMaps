package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import br.com.moreira.googleMapsLeeds.service.ComerciosTransicaoService;
import br.com.moreira.googleMapsLeeds.service.ServiceHome;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ControllerHome{
    private ServiceHome serviceHome;
    private ComerciosTransicaoService comerciosTransicaoService;
    private EntityManagerFactory entityManagerFactory;

    public ControllerHome(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.serviceHome = new ServiceHome();
        this.comerciosTransicaoService = new ComerciosTransicaoService(entityManagerFactory);
    }

    public void BuscaLocal(String location, AtomicBoolean interromperThread) throws IOException, InterruptedException {
        List<String> commerceList = serviceHome.NearbySearch(location);
        for(String commerce : commerceList){
            if(interromperThread.get()){
                System.out.println("Processo interrompido");
                return;
            }
            ComerciosTransicaoModel commerceDetails = serviceHome.PlaceDetails(commerce);
            comerciosTransicaoService.AddCommerceListInDataBase(commerceDetails);
        }
        System.out.println("PROCESSO FINALIZADO");
    }

    public List<ComerciosTransicaoDTO> listarComerciosTransicao(){
        List<ComerciosTransicaoDTO> comercios = comerciosTransicaoService.listar();
        System.out.println("LISTAGEM DE COMERCIOS REALIZADA");
        return comercios;
    }

}
