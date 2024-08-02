package br.com.moreira.googleMapsLeeds.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailsDTO {
    @JsonProperty("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PlaceDetailsDTO{" +
                "result=" + result +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result{
        @JsonProperty("name")
        private String name;
        @JsonProperty("types")
        private List<String> types;
        @JsonProperty("international_phone_number")
        private String phoneNumber;
        @JsonProperty("website")
        private String site;
        @JsonProperty("address_components")
        private List<AddresComponent> addresComponents;

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public List<AddresComponent> getAddresComponents() {
            return addresComponents;
        }

        public void setAddresComponents(List<AddresComponent> addresComponents) {
            this.addresComponents = addresComponents;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class AddresComponent{
            @JsonProperty("long_name")
            private String long_name;
            @JsonProperty("short_name")
            private String short_name;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }
        }
    }
}
