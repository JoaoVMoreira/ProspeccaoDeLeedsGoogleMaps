package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.DTO.ApiKeyDTO;
import br.com.moreira.googleMapsLeeds.DTO.QrcodeDTO;
import br.com.moreira.googleMapsLeeds.DTO.RaioDTO;
import br.com.moreira.googleMapsLeeds.Main;
import br.com.moreira.googleMapsLeeds.controller.ControllerWhatsAppAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigViewController implements Initializable {

    private ControllerWhatsAppAPI controller = new ControllerWhatsAppAPI();
    public static Stage QRcodeModalStage;
    public static String qrCodeString;
    public ObjectMapper objectMapper = new ObjectMapper();

    @FXML
    private Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private Alert negativeAlert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private TextField apiKeyText;
    @FXML
    private Slider slider = new Slider(10, 100, 10);
    @FXML
    private Label labelSlider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setMajorTickUnit(100);
        slider.setBlockIncrement(10);
        slider.setMinorTickCount(9);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            slider.setValue(newVal.intValue());
            int valueSlider = (int)slider.getValue()*10;
            labelSlider.setText(""+valueSlider);
        });


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
    @FXML
    void setApiKey(){
        String apiKey = apiKeyText.getText();
        if (apiKey.length() != 0){
            generateApiKeyJson(apiKey);
            confirmationAlert.setTitle("CHAVE DE API INSERIDA COM SUCESSO");
            confirmationAlert.showAndWait();
        }else{
            negativeAlert.setTitle("CHAVE DE API INVÁLIDA");
            negativeAlert.showAndWait();
        }
    }
    @FXML
    void setRaio(){
        int value = (int)slider.getValue() * 10;
        generateRaioJson(value);
        if (value == 0){
            negativeAlert.setTitle("O VALOR EDEVE SER DIFERENTE DE 0");
            negativeAlert.showAndWait();
        }else{
            confirmationAlert.setTitle("RAIO DEFINIDO COM SUCESSO: " + value + "M²");
            confirmationAlert.showAndWait();
        }
    }

    private void generateApiKeyJson(String apiKey){
        ApiKeyDTO apiKeyDTO = new ApiKeyDTO(apiKey);
        try{
            objectMapper.writeValue(new File("src\\main\\java\\br\\com\\moreira\\googleMapsLeeds\\json\\apiKey.json"), apiKeyDTO);
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void generateRaioJson(int raio){
        RaioDTO raioDTO = new RaioDTO(raio);
        try{
            objectMapper.writeValue(new File("src\\main\\java\\br\\com\\moreira\\googleMapsLeeds\\json\\raio.json"), raioDTO);
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void clickAndShow() throws IOException {
        if (QRcodeModalStage == null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/qrcodeView.fxml"));
            Parent root = loader.load();

            QRcodeModalStage = new Stage();
            QRcodeModalStage.setScene(new Scene(root));
            QRcodeModalStage.setTitle("My modal window");
            QRcodeModalStage.setResizable(false);
            QRcodeModalStage.initModality(Modality.APPLICATION_MODAL);
        }
        QRcodeModalStage.showAndWait();
    }
}
