package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.controller.ControllerComercios;
import br.com.moreira.googleMapsLeeds.controller.ControllerComerciosTransicao;
import br.com.moreira.googleMapsLeeds.controller.ControllerMapsAPI;
import br.com.moreira.googleMapsLeeds.controller.ControllerWhatsAppAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class HomeViewController implements Initializable {

    @FXML
    private TableView<ComerciosTransicaoDTO> tableCommerce;
    @FXML
    private TableColumn<ComerciosTransicaoDTO, String> colNome;
    @FXML
    private TableColumn<ComerciosTransicaoDTO, String> colSegmento;
    @FXML
    private TableColumn<ComerciosTransicaoDTO, String> colCidade;
    @FXML
    private TableColumn<ComerciosTransicaoDTO, String> colNumero;
    @FXML
    private TableColumn<ComerciosTransicaoDTO, String> colSite;
    @FXML
    private TextField coordText;
    @FXML
    private Button deleteButton;
    @FXML
    private Button startButton;
    @FXML
    private Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    private ControllerMapsAPI controllerMapsAPI = new ControllerMapsAPI();
    private ControllerComerciosTransicao transicaoController = new ControllerComerciosTransicao();
    private AtomicBoolean interromperThread = new AtomicBoolean(false);
    private ControllerComercios controllerComercios = new ControllerComercios();
    private ControllerWhatsAppAPI controllerWhatsAppAPI = new ControllerWhatsAppAPI();

    @FXML
    public void buscarComercios() throws IOException, InterruptedException {
        interromperThread.set(false);
        Runnable task = () -> {
            try{
                String coordenadas = coordText.getText();
                coordText.setDisable(true);
                startButton.setDisable(true);

                controllerMapsAPI.BuscaLocal(coordenadas, interromperThread);

                coordText.setText("");
                preencherTable();
                coordText.setDisable(false);
                startButton.setDisable(false);
                alertConfirmation.setTitle("Processo finalizado com sucesso");
                alertConfirmation.setContentText("Listagem de comercios finalizada com sucesso");
                alertConfirmation.showAndWait();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        };
        Thread searchLocalThread = new Thread(task);
        searchLocalThread.setDaemon(true);
        searchLocalThread.start();
    }

    @FXML
    private void deleteItem(){
        List<ComerciosTransicaoDTO> selectedItens = tableCommerce.getSelectionModel().getSelectedItems();
        transicaoController.deleteItens(selectedItens);
        preencherTable();
    }

    @FXML
    private void cancelarBusca() throws IOException, InterruptedException {
        interromperThread.set(true);
        coordText.setDisable(false);
        startButton.setDisable(false);

        alertWarning.setTitle("Processo interrompido");
        alertWarning.setContentText("Processo cancelado com sucesso!");
        alertWarning.showAndWait();
    }

    @FXML
    public void verifyContacts() throws IOException, InterruptedException {
        ObservableList<ComerciosTransicaoDTO> comercios = FXCollections.observableArrayList(transicaoController.listarComerciosTransicao());
        controllerWhatsAppAPI.VerifyContact(comercios);
    }

    @FXML
    public void moveToMainDB(){
        controllerComercios.MoveToMainCommerce();
        preencherTable();
        alertConfirmation.setTitle("Informações movidas ao DB principal");
        alertConfirmation.setContentText("Processo realizado com sucesso!");
        alertConfirmation.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableCommerce.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSegmento.setCellValueFactory(new PropertyValueFactory<>("segmento"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("contato"));
        colSite.setCellValueFactory(new PropertyValueFactory<>("site"));
        preencherTable();
    }

    private void preencherTable() {
        ObservableList<ComerciosTransicaoDTO> comercios = FXCollections.observableArrayList(transicaoController.listarComerciosTransicao());
        tableCommerce.setItems(comercios);
    }

}
