package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.DTO.QrcodeDTO;
import br.com.moreira.googleMapsLeeds.infra.Factory;
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
    public void VerifyContact(List<ComerciosTransicaoDTO> commerceList) throws IOException, InterruptedException {
        service.verifyContact(commerceList);
    }
    public boolean testConnect() throws IOException, InterruptedException {
        return service.verifySession();
    }
    public boolean cancelSession() throws IOException, InterruptedException {
        return service.cancelSession();
    }
    public QrcodeDTO startSession() throws IOException, InterruptedException {
        QrcodeDTO qrCodeGen = service.startSession();
        return qrCodeGen;
    }

}
