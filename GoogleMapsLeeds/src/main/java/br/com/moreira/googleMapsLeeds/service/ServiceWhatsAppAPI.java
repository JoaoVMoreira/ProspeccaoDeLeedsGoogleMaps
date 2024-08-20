package br.com.moreira.googleMapsLeeds.service;

import br.com.moreira.googleMapsLeeds.DTO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nayuki.qrcodegen.QrCode;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
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
    public boolean verifySession() throws IOException, InterruptedException {
        String requestLink = "http://localhost:3000/session/status/testMain";
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(requestLink))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ConnectionStatusDTO success = mapper.readValue(response.body(), ConnectionStatusDTO.class);
        return success.isSuccess();
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
    public boolean cancelSession() throws IOException, InterruptedException {
        String linkRequest = "http://localhost:3000/session/terminate/testMain";
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("x-api-key", "x-api-key")
                .uri(URI.create(linkRequest))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ConnectionStatusDTO result = mapper.readValue(response.body(), ConnectionStatusDTO.class);
        return result.isSuccess();
    }
    public QrcodeViewDTO startSession() throws IOException, InterruptedException {
        String linkRequest = "http://localhost:3000/session/start/testMain";
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("x-api-key", "testMain")
                .uri(URI.create(linkRequest))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ConnectionStatusDTO result = mapper.readValue(response.body(), ConnectionStatusDTO.class);
        if(result.isSuccess()){
            QrcodeDTO qr = getQRCode();
            while (!qr.isSuccess()){
                qr = getQRCode();
            }
            return new QrcodeViewDTO(qr.isSuccess(), convertToQRCode(qr.getQr()));
        }else{
            System.out.println("Erro ao iniciar sessão");
            return null;
        }
    }
    private QrcodeDTO getQRCode() throws IOException, InterruptedException {
        String linkRequest = "http://localhost:3000/session/qr/testMain";
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("x-api-key", "x-api-key")
                .uri(URI.create(linkRequest))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), QrcodeDTO.class);
    }
    private String trataContato(String contato){
        return contato.replace(" ", "").replace("+", "").replace("-", "");
    }
    private QrCode convertToQRCode(String qr){
        return QrCode.encodeText(qr, QrCode.Ecc.MEDIUM);
    }
}
