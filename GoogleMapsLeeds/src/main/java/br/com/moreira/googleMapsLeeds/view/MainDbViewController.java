package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.Main;
import br.com.moreira.googleMapsLeeds.controller.ControllerComercios;
import br.com.moreira.googleMapsLeeds.model.ComerciosModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainDbViewController implements Initializable {

    @FXML
    private TableView<ComerciosModel> tableCommerce;
    @FXML
    private TableColumn<ComerciosModel, String> colNome;
    @FXML
    private TableColumn<ComerciosModel, String> colSegmento;
    @FXML
    private TableColumn<ComerciosModel, String> colCidade;
    @FXML
    private TableColumn<ComerciosModel, String> colNumero;
    @FXML
    private TableColumn<ComerciosModel, String> colSite;

    private ControllerComercios controller = new ControllerComercios();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableCommerce.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colSegmento.setCellValueFactory(new PropertyValueFactory<>("segmento"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("contato"));
        colSite.setCellValueFactory(new PropertyValueFactory<>("site"));
        preencherMainTable();
    }

    @FXML
    void home(){
        Main.changeScreen("home");
        preencherMainTable();
    }
    @FXML
    private void delete(){
        List<ComerciosModel> commerces = tableCommerce.getSelectionModel().getSelectedItems();
        controller.DeleteCommerces(commerces);
        preencherMainTable();
    }
    @FXML
    void config(){
        Main.changeScreen("config");
    }
    @FXML
    void whatsAppPage(){
        Main.changeScreen("whatsApp");
    }
    @FXML
    private void keyEventController(KeyEvent event) throws IOException, InterruptedException {
        if(event.getCode() == KeyCode.DELETE){
            delete();
        }
    }
    @FXML
    public void exportExcel() throws IOException {
        controller.exportExcel();
    }
    public void preencherMainTable(){
        ObservableList<ComerciosModel> commerces = FXCollections.observableArrayList(controller.ListCommerces());
        tableCommerce.setItems(commerces);
    }


}
