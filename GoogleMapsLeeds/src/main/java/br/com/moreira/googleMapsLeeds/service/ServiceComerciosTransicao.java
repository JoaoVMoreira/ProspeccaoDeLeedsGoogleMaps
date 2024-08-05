package br.com.moreira.googleMapsLeeds.service;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceComerciosTransicao {

    private EntityManagerFactory factory;

    public ServiceComerciosTransicao(EntityManagerFactory entityManagerFactory){
        this.factory = entityManagerFactory;
    }
    public List<ComerciosTransicaoDTO> listar(){
        String query = "FROM comercioTransacao c";
        EntityManager entityManager = factory.createEntityManager();
        try{
            List<ComerciosTransicaoModel> result = entityManager.createQuery(query, ComerciosTransicaoModel.class).getResultList();
            return result.stream().map(c -> new ComerciosTransicaoDTO(c.getId(), c.getNome(), c.getSegmento(), c.getCidade(), c.getContato(), c.getSite())).collect(Collectors.toList());
        }catch (Exception e){
            System.out.println(e);
            return null;
        }finally {
            entityManager.close();
        }
    }
    public void AddCommerceListInDataBase(ComerciosTransicaoModel data){
        EntityTransaction transaction = null;
        EntityManager entityManager = factory.createEntityManager();
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(data);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            entityManager.close();
        }
    }
    public void DeleteCommercesFromDatabase(String id){
        ComerciosTransicaoModel comercio = null;
        EntityTransaction transaction = null;
        EntityManager entityManager = factory.createEntityManager();
        try{
            transaction = entityManager.getTransaction();
            comercio = entityManager.find(ComerciosTransicaoModel.class, id);
            transaction.begin();
            entityManager.remove(comercio);
            transaction.commit();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
