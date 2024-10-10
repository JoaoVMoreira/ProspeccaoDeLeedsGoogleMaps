package br.com.moreira.googleMapsLeeds.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageJSON {
    @JsonProperty("message")
    private String message;

    public MessageJSON(String message){
        this.message = message;
    }

    public MessageJSON(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
