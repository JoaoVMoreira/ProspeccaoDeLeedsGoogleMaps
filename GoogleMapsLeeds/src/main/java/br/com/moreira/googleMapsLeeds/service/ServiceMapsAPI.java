package br.com.moreira.googleMapsLeeds.service;

import br.com.moreira.googleMapsLeeds.DTO.NearbySearchDTO;
import br.com.moreira.googleMapsLeeds.DTO.NextPageDTO;
import br.com.moreira.googleMapsLeeds.DTO.PlaceDetailsDTO;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceMapsAPI {

    private ObjectMapper mapper = new ObjectMapper();

    public String formatCoord(String coord){
        return coord.replace(" ", "");
    }

    public List<String> NearbySearch(String location) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        String formatedLocation = formatCoord(location);
        String requestLink = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+formatedLocation+"&radius=1000&key=AIzaSyATIq7TrwrW34WT2PHwwuwdm7yokPCKYeA";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestLink))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String firstResultNextPage = mapper.readValue(response.body(), NextPageDTO.class).getNextPage();
        NearbySearchDTO firstResultNearbySearch = mapper.readValue(response.body(), NearbySearchDTO.class);
        return NextPage(firstResultNextPage, client, firstResultNearbySearch);
    }

    public List<String> NextPage(String responseFirstNextPage, HttpClient client, NearbySearchDTO resultFistPage) throws IOException, InterruptedException {
        List<String> placesIdList = new ArrayList<>();
        placesIdList.addAll(resultFistPage.getResult().stream()
                .map(NearbySearchDTO.Result::getPlace_id)
                .collect(Collectors.toList()));

        while (responseFirstNextPage != null) {
            Thread.sleep(2000);
            String linkHttpNextPage = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?pagetoken="+responseFirstNextPage+"&key=AIzaSyATIq7TrwrW34WT2PHwwuwdm7yokPCKYeA";
            HttpRequest requestNextPage = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(linkHttpNextPage))
                    .build();
            HttpResponse<String> responseNextPage = client.send(requestNextPage, HttpResponse.BodyHandlers.ofString());
            NextPageDTO nextPageResult = mapper.readValue(responseNextPage.body(), NextPageDTO.class);
            NearbySearchDTO result = mapper.readValue(responseNextPage.body(), NearbySearchDTO.class);
            placesIdList.addAll(result.getResult().stream()
                    .map(NearbySearchDTO.Result::getPlace_id)
                    .collect(Collectors.toList()));
            responseFirstNextPage = nextPageResult.getNextPage();
        }
        return placesIdList;
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
            PlaceDetailsDTO placeDetails = mapper.readValue(response.body(), PlaceDetailsDTO.class);
            return new ComerciosTransicaoModel(placeDetails);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
