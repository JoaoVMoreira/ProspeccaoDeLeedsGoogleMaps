package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.DTO.QrcodeDTO;
import br.com.moreira.googleMapsLeeds.Main;
import br.com.moreira.googleMapsLeeds.controller.ControllerWhatsAppAPI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigViewController implements Initializable {

    private ControllerWhatsAppAPI controller = new ControllerWhatsAppAPI();
    public static Stage QRcodeModalStage;
    public static String qrCodeString;
    public static String apiKey;

    @FXML
    private Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private Alert negativeAlert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private TextField apiKeyText;

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
            confirmationAlert.setTitle("SUCESSO!");
            confirmationAlert.setContentText("Conexão ativa");
            confirmationAlert.showAndWait();
        }else {
            negativeAlert.setTitle("ERRO!");
            negativeAlert.setContentText("Conexão não está ativa");
            negativeAlert.showAndWait();
        }
    }
    @FXML
    void cancelConnection() throws IOException, InterruptedException {
        boolean sessionReturn = controller.cancelSession();
        if (sessionReturn){
            confirmationAlert.setTitle("SUCESSO");
            confirmationAlert.setContentText("Sessão finalizada com sucesso");
            confirmationAlert.showAndWait();
        }else{
            negativeAlert.setTitle("ERRO");
            negativeAlert.setContentText("A sessão não pode ser finalizada");
            negativeAlert.showAndWait();
        }
    }
    @FXML
    void startConnection() throws IOException, InterruptedException {
        QrcodeDTO qrCode = controller.startSession();
        if(qrCode.isSuccess()){
            qrCodeString = qrCode.getQr();
            clickAndShow();
        }else{
            negativeAlert.setTitle("ERRO");
            negativeAlert.setContentText("A sessão não pode ser iniciada");
            negativeAlert.showAndWait();
        }
    }

    public void clickAndShow() throws IOException {
        if (QRcodeModalStage == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/qrcodeView.fxml"));
            Parent root = loader.load();

            QRcodeModalStage = new Stage();
            QRcodeModalStage.setScene(new Scene(root));
            QRcodeModalStage.setTitle("My modal window");
            QRcodeModalStage.initModality(Modality.APPLICATION_MODAL);
        }
        QRcodeModalStage.showAndWait();
    }

    @FXML
    public void setApiKey(){
        apiKey = apiKeyText.getText();
        if (apiKey.length() != 0){
            System.out.println(apiKey);
            confirmationAlert.setTitle("CHAVE DE API INSERIDA COM SUCESSO");
            confirmationAlert.showAndWait();
        }else{
            negativeAlert.setTitle("CHAVE DE API INVÁLIDA");
            negativeAlert.showAndWait();
        }

    }
}
