package br.com.moreira.googleMapsLeeds.view;

import br.com.moreira.googleMapsLeeds.model.ComerciosTransicaoModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private TableView<ComerciosTransicaoModel> tableCommerce = new TableView<ComerciosTransicaoModel>();

    @FXML
    private TableColumn<ComerciosTransicaoModel, String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<ComerciosTransicaoModel, String> colSegmento= new TableColumn<>();

    @FXML
    private TableColumn<ComerciosTransicaoModel, String> colCidade = new TableColumn<>();

    @FXML
    private TableColumn<ComerciosTransicaoModel, String> colNumero = new TableColumn<>();

    @FXML
    private TableColumn<ComerciosTransicaoModel, String> colSite = new TableColumn<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNome.setCellValueFactory(new PropertyValueFactory<ComerciosTransicaoModel, String>("nome"));
        colSegmento.setCellValueFactory(new PropertyValueFactory<ComerciosTransicaoModel, String>("segmento"));
        colCidade.setCellValueFactory(new PropertyValueFactory<ComerciosTransicaoModel, String>("cidade"));
        colNumero.setCellValueFactory(new PropertyValueFactory<ComerciosTransicaoModel, String>("contato"));
        colSite.setCellValueFactory(new PropertyValueFactory<ComerciosTransicaoModel, String>("site"));
        //Chamar metodo preencherTable()
    }

    private void preencherTable(){
        //Acessar db e criar um objeto ComerciosTransicao
        //tableCommerce.setItems(commerces);
    }
}
