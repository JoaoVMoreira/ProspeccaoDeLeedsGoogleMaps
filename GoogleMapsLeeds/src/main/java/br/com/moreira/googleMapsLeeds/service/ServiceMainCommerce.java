package br.com.moreira.googleMapsLeeds.service;

import br.com.moreira.googleMapsLeeds.controller.ControllerComerciosTransicao;
import br.com.moreira.googleMapsLeeds.model.ComerciosModel;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceMainCommerce {
    private EntityManagerFactory factory;
    private ServiceTransitionCommerce serviceTransitionCommerce;
    private ControllerComerciosTransicao controllerComerciosTransicao;

    public ServiceMainCommerce(EntityManagerFactory factory){
        this.factory = factory;
        this.serviceTransitionCommerce = new ServiceTransitionCommerce(factory);
    }

    private List<ComerciosModel> ListCommercesToMainDB(){
        String query = "FROM comercioTransacao C";
        EntityManager em = factory.createEntityManager();
        try{
            List<ComerciosTransicaoModel> result = em.createQuery(query, ComerciosTransicaoModel.class).getResultList();
            return result.stream().map(c -> new ComerciosModel(c.getId(), c.getNome(), c.getSegmento(), c.getContato(), c.getSite(), c.getCttRealizado(), c.getCidade(), c.getPossuiWpp())).collect(Collectors.toList());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            em.close();
        }
    }

    public void addCommercesInMainDB(){
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = null;
        List<ComerciosModel> data = ListCommercesToMainDB();
        try{
            transaction = em.getTransaction();
            transaction.begin();
            for (ComerciosModel i : data){
                em.merge(i);
            }
            transaction.commit();
            serviceTransitionCommerce.DeleteAllFromTranditionDB();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            em.close();
        }
    }

}
