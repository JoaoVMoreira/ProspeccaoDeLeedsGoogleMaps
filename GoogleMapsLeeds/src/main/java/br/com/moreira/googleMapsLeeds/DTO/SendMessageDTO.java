package br.com.moreira.googleMapsLeeds.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMessageDTO {

    @JsonProperty("success")
    private Boolean success;

    public SendMessageDTO(Boolean success){
        this.success = success;
    }

    public SendMessageDTO(){}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
