package br.com.moreira.googleMapsLeeds.service;

import br.com.moreira.googleMapsLeeds.DTO.NearbySearchDTO;
import br.com.moreira.googleMapsLeeds.DTO.PlaceDetailsDTO;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceHome {
    private EntityManager entityManager;

    public ServiceHome(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<String> NearbySearch(String location) throws IOException, InterruptedException {
        try{
            HttpClient client = HttpClient.newBuilder().build();
            String linkHttp = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location+"&radius=1000&key=AIzaSyATIq7TrwrW34WT2PHwwuwdm7yokPCKYeA";
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(linkHttp))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            NearbySearchDTO result = mapper.readValue(response.body(), NearbySearchDTO.class);
            List<String> placesIdList = result.getResult().stream().map(NearbySearchDTO.Result::getPlace_id).collect(Collectors.toList());
            return placesIdList;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ComerciosTransicaoModel PlaceDetails(String data){
        try{
            HttpClient client = HttpClient.newBuilder().build();
            String linkHttp = "https://maps.googleapis.com/maps/api/place/details/json?place_id="+data+"&key=AIzaSyATIq7TrwrW34WT2PHwwuwdm7yokPCKYeA";
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(linkHttp))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            PlaceDetailsDTO placeDetails = mapper.readValue(response.body(), PlaceDetailsDTO.class);
            return new ComerciosTransicaoModel(placeDetails);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void AddCommerceListInDataBase(ComerciosTransicaoModel data){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //RemoveCommerceFromDataBase
    //MoveCommercesToOficialDataBase


}
