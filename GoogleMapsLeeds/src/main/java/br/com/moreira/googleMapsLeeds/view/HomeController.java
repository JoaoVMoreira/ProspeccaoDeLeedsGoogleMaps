package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.controller.ControllerHome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class HomeController implements Initializable {

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
    private Button startButton;
    @FXML
    private Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private Alert alertWarning = new Alert(Alert.AlertType.WARNING);

    private ControllerHome controller = new ControllerHome();
    private AtomicBoolean interromperThread = new AtomicBoolean(false);

    @FXML
    public void buscarComercios() throws IOException, InterruptedException {
        interromperThread.set(false);
        Runnable task = () -> {
            try{
                String coordenadas = coordText.getText();
                coordText.setDisable(true);
                startButton.setDisable(true);

                controller.BuscaLocal(coordenadas, interromperThread);

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
    private void cancelarBusca(){
        interromperThread.set(true);
        coordText.setDisable(false);
        startButton.setDisable(false);


        alertWarning.setTitle("Processo interrompido");
        alertWarning.setContentText("Processo cancelado com sucesso!");
        alertWarning.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSegmento.setCellValueFactory(new PropertyValueFactory<>("segmento"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("contato"));
        colSite.setCellValueFactory(new PropertyValueFactory<>("site"));
        preencherTable();
    }

    private void preencherTable() {
        ObservableList<ComerciosTransicaoDTO> comercios = FXCollections.observableArrayList(controller.listarComerciosTransicao());
        tableCommerce.setItems(comercios);
    }


}
