package br.com.moreira.googleMapsLeeds.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiKeyDTO {
    @JsonProperty("apiKey")
    private String apiKey;

    public ApiKeyDTO(String apiKey){
        this.apiKey = apiKey;
    }

    public ApiKeyDTO(){

    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
