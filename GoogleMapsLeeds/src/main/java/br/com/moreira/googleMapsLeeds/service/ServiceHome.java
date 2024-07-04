package br.com.moreira.googleMapsLeeds.service;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ServiceHome {
    private EntityManager entityManager;

    public ServiceHome(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public String NearbySearch(String location) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        String linkHttp = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location+"&radius=1000&key=AIzaSyATIq7TrwrW34WT2PHwwuwdm7yokPCKYeA";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(linkHttp))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
        //Deve retornar uma lista com os place_id
    }

    public void AddCommerceListInDataBase(List<String> comercios_transicao){
        //adicionar lista recebida ao DB de transição
    }

    //RemoveCommerceFromDataBase
    //MoveCommercesToOficialDataBase


}
