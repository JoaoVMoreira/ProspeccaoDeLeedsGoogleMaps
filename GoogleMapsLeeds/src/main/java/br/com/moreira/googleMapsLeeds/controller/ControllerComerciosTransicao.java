package br.com.moreira.googleMapsLeeds.controller;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.infra.Factory;
import br.com.moreira.googleMapsLeeds.service.ServiceComerciosTransicao;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ControllerComerciosTransicao {
    private ServiceComerciosTransicao serviceComerciosTransicao;
    private EntityManagerFactory entityManagerFactory;

    public ControllerComerciosTransicao(){
        this.entityManagerFactory = Factory.getEntityManagerFactory();
        this.serviceComerciosTransicao = new ServiceComerciosTransicao(entityManagerFactory);
    }

    public List<ComerciosTransicaoDTO> listarComerciosTransicao(){
        List<ComerciosTransicaoDTO> comercios = serviceComerciosTransicao.listar();
        return comercios;
    }

    public void deleteItens(List<ComerciosTransicaoDTO> idList){
        for(ComerciosTransicaoDTO id : idList){
            serviceComerciosTransicao.DeleteCommercesFromDatabase(id.getId());
        }
    }
}
