package br.com.moreira.googleMapsLeeds.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionStatusDTO {

    @JsonProperty("success")
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

}
