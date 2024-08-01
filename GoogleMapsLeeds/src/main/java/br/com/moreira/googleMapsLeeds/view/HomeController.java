package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.DTO.ComerciosTransicaoDTO;
import br.com.moreira.googleMapsLeeds.controller.ControllerHome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private ControllerHome controller = new ControllerHome();

    @FXML
    public void buscarComercios() throws IOException, InterruptedException {
        String coordenadas = coordText.getText();
        controller.BuscaLocal(coordenadas);
        coordText.setText("");
        preencherTable();
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
