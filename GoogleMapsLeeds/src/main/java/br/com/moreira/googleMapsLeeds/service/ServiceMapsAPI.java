package br.com.moreira.googleMapsLeeds.service;

import br.com.moreira.googleMapsLeeds.DTO.*;
import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import br.com.moreira.googleMapsLeeds.view.ConfigViewController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
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
    private String apiKey;
    private int raio;
    File apiKeyFile = new File("C:\\\\Users\\\\jvitt\\\\Documents\\\\ProspecLeedsGoogleMaps\\GoogleMapsLeeds\\src\\main\\java\\br\\com\\moreira\\googleMapsLeeds\\json\\apiKey.json");
    File raioFile = new File("C:\\\\Users\\\\jvitt\\\\Documents\\\\ProspecLeedsGoogleMaps\\GoogleMapsLeeds\\src\\main\\java\\br\\com\\moreira\\googleMapsLeeds\\json\\raio.json");
//AIzaSyATIq7TrwrW34WT2PHwwuwdm7yokPCKYeA
    public String formatCoord(String coord){
        return coord.replace(" ", "");
    }
    public List<String> NearbySearch(String location) throws IOException, InterruptedException {
        apiKey = mapper.readValue(apiKeyFile, ApiKeyDTO.class).getApiKey();
        raio = mapper.readValue(raioFile, RaioDTO.class).getRaio();
        HttpClient client = HttpClient.newBuilder().build();
        String formatedLocation = formatCoord(location);
        String requestLink = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+formatedLocation+"&radius="+raio+"&key=" + apiKey;
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
        apiKey = mapper.readValue(apiKeyFile, ApiKeyDTO.class).getApiKey();
        List<String> placesIdList = new ArrayList<>();
        placesIdList.addAll(resultFistPage.getResult().stream()
                .map(NearbySearchDTO.Result::getPlace_id)
                .collect(Collectors.toList()));

        while (responseFirstNextPage != null) {
            Thread.sleep(2000);
            String linkHttpNextPage = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?pagetoken="+responseFirstNextPage+"&key="+ apiKey;
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
            apiKey = mapper.readValue(apiKeyFile, ApiKeyDTO.class).getApiKey();
            HttpClient client = HttpClient.newBuilder().build();
            String linkHttp = "https://maps.googleapis.com/maps/api/place/details/json?place_id="+data+"&key=" + apiKey;
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
