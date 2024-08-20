package br.com.moreira.googleMapsLeeds.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class QrcodeViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void closeModal(){
        if(ConfigViewController.QRcodeModalStage != null){
            ConfigViewController.QRcodeModalStage.close();
            ConfigViewController.QRcodeModalStage = null;
        }
    }
}
