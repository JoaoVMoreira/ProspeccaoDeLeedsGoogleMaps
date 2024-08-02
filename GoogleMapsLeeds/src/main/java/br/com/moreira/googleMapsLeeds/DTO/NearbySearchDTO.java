package br.com.moreira.googleMapsLeeds.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NearbySearchDTO {

    @JsonProperty("results")
    private List<Result> result;

    public List<Result> getResult(){
        return result;
    }

    @Override
    public String toString() {
        return "NearbySearchDTO{" +
                "result=" + result +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result{

        @JsonProperty("place_id")
        private String place_id;

        public String getPlace_id(){
            return place_id;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "place_id='" + place_id + '\'' +
                    '}';
        }
    }
}
