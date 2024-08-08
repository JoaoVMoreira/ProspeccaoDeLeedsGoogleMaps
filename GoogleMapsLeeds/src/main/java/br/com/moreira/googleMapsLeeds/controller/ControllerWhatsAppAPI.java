package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import br.com.moreira.googleMapsLeeds.service.ServiceWhatsAppAPI;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;


public class ControllerWhatsAppAPI {
    private EntityManagerFactory entityManagerFactory;
    private ServiceWhatsAppAPI service;

    public ControllerWhatsAppAPI(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.service = new ServiceWhatsAppAPI(entityManagerFactory);
    }
    public void StartSession() throws IOException, InterruptedException {
        service.startSession();
    }
    public void VerifyContact(List<ComerciosTransicaoDTO> commerceList) throws IOException, InterruptedException {
        service.verifyContact(commerceList);
    }
}
