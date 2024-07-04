package br.com.moreira.googleMapsLeeds;


import br.com.moreira.googleMapsLeeds.controller.ControllerHome;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ControllerHome controller = new ControllerHome();
        controller.BuscaLocal("-23.550520,-46.633308");
    }
}
