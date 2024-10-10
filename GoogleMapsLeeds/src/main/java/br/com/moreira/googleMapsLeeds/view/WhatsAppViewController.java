package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.DTO.MessageJSON;
import br.com.moreira.googleMapsLeeds.Main;
import br.com.moreira.googleMapsLeeds.controller.ControllerComercios;
import br.com.moreira.googleMapsLeeds.controller.ControllerWhatsAppAPI;
import br.com.moreira.googleMapsLeeds.model.ComerciosModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WhatsAppViewController implements Initializable {

    @FXML
    private TableView <ComerciosModel> tableCommerce;
    @FXML
    private TableColumn <ComerciosModel, String> tb_nome;
    @FXML
    private TableColumn <ComerciosModel, String> tb_contato;
    @FXML
    private TextArea messageInput;
    @FXML
    private Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    private ControllerComercios controller = new ControllerComercios();
    private ControllerWhatsAppAPI controllerWhatsAppAPI = new ControllerWhatsAppAPI();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        tableCommerce.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tb_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tb_contato.setCellValueFactory(new PropertyValueFactory<>("contato"));
        try {
            preencherTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        File jsonMessage = new File("src\\main\\java\\br\\com\\moreira\\googleMapsLeeds\\json\\mensagem.json");
        String message = "";
        try {
            message = objectMapper.readValue(jsonMessage, MessageJSON.class).getMessage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        messageInput.setText(message);
    }
    private void preencherTable() throws IOException, InterruptedException {
        List<ComerciosModel> comercios = controller.FilterCommercesWhatsApp(controller.ListCommerces());
        ObservableList<ComerciosModel> commerces = FXCollections.observableArrayList(comercios);
        tableCommerce.setItems(commerces);
    }
    private void messageJson(MessageJSON mensagem){
        try{
            objectMapper.writeValue(new File("src\\main\\java\\br\\com\\moreira\\googleMapsLeeds\\json\\mensagem.json"), mensagem);
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void homePage(){
        Main.changeScreen("home");
    }
    @FXML
    void mainDbPage(){
        Main.changeScreen("mainDb");
    }
    @FXML
    void configPage(){
        Main.changeScreen("config");
    }
    @FXML
    void setMessage(){
        try{
            String message = messageInput.getText();
            MessageJSON mJSON = new MessageJSON(message);
            messageJson(mJSON);
            alertConfirmation.setTitle("Mensagem salva com sucesso");
            alertConfirmation.showAndWait();
        }catch (Exception e){
            alertWarning.setTitle("Erro ao salvar mensagem");
            alertWarning.showAndWait();
        }

    }
    @FXML
    void deleteCommerce() throws IOException, InterruptedException {
        List<ComerciosModel> commerces = tableCommerce.getSelectionModel().getSelectedItems();
        controller.DeleteCommerces(commerces);
        preencherTable();
    }
    @FXML
    void sendMessage() throws IOException, InterruptedException {
        System.out.println("OK");
        List<ComerciosModel> commerces = tableCommerce.getSelectionModel().getSelectedItems();
        try{
            controllerWhatsAppAPI.sendMessage(commerces);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }

}
