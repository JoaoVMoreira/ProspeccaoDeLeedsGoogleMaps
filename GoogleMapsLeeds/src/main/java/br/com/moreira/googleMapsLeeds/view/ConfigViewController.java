package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.Main;
import br.com.moreira.googleMapsLeeds.controller.ControllerWhatsAppAPI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigViewController implements Initializable {

    private ControllerWhatsAppAPI controller = new ControllerWhatsAppAPI();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void homePage(){
        Main.changeScreen("home");
    }

    @FXML
    void testConnection() throws IOException, InterruptedException {
        boolean success = controller.testConnect();
        if(success){
            System.out.println("CONEXÃO REALIZADA");
        }else {
            System.out.println("CONEXÃO NÃO REALIZADA");
        }
    }
}
