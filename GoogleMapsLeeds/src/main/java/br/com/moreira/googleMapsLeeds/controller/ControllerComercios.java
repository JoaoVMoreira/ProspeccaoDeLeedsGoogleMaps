package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.model.ComerciosModel;
import br.com.moreira.googleMapsLeeds.service.ServiceMainCommerce;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;

public class ControllerComercios {
    private EntityManagerFactory entityManagerFactory;
    private ServiceMainCommerce service;

    public ControllerComercios(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.service = new ServiceMainCommerce(entityManagerFactory);
    }
    public void MoveToMainCommerce(){
        service.addCommercesInMainDB();
    }
    public List<ComerciosModel> ListCommerces(){
        return service.ListMainCommerces();
    }
    public void DeleteCommerces(List<ComerciosModel> commerces){
        for(ComerciosModel id : commerces){
            service.deleteItensFromMainDB(id.getId());
        }
    }
    public void exportExcel() throws IOException {
        service.exportExcel();
    }
    public List<ComerciosModel> FilterCommercesWhatsApp(List<ComerciosModel> commerces) throws IOException, InterruptedException {
        return service.FilterCommercesWhatsApp(commerces);
    }
}
