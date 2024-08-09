package br.com.moreira.googleMapsLeeds.service;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.DTO.VerifyContactDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.*;
import java.net.http.HttpResponse;
import java.util.List;

public class ServiceWhatsAppAPI {
    private ObjectMapper mapper;
    private ServiceTransitionCommerce serviceTransitionCommerce;
    private EntityManagerFactory factory;

    public ServiceWhatsAppAPI(EntityManagerFactory factory){
        this.mapper = new ObjectMapper();
        this.serviceTransitionCommerce = new ServiceTransitionCommerce(factory);
        this.factory = factory;
    }

    public boolean startSession() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        String requestLink = "http://localhost:3000/session/start/testMain";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("x-api-key", "testMain")
                .uri(URI.create(requestLink))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
        //iniciar sessão
        //Verifica se a sessão esta iniciada
        return true;
    }

    private boolean verifySessio(){
        //http://localhost:3000/session/status/{NomeSessão}
        //  *"sucess": true
        //
        //verificar se a sessão esta ativa
        return true;
    }

    public void verifyContact(List<ComerciosTransicaoDTO> listCommerce) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        String linkRequest = "http://localhost:3000/client/isRegisteredUser/testMain";
        String contact = "";
        for (ComerciosTransicaoDTO i : listCommerce){
            if(i.getContato() == null){
                serviceTransitionCommerce.alterCommerce(i.getId());
                continue;
            }
            contact = trataContato(i.getContato());
            String requestJSON = "{\"number\": \"" + contact +"\"}";
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(BodyPublishers.ofString(requestJSON))
                    .header("Content-Type", "application/json")
                    .uri(URI.create(linkRequest))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            VerifyContactDTO contactOk = mapper.readValue(response.body(), VerifyContactDTO.class);

            if(!contactOk.isResult()){
                serviceTransitionCommerce.alterCommerce(i.getId());
            }
        }
    }

    private String trataContato(String contato){
        return contato.replace(" ", "").replace("+", "").replace("-", "");
    }
}
