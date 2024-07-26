package br.com.moreira.googleMapsLeeds;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("My Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //ControllerHome controller = new ControllerHome();
        //controller.BuscaLocal("-23.618891908092493,-46.849262567204946");
        launch(args);
    }

}
