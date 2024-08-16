package br.com.moreira.googleMapsLeeds;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    private static Scene homeScene;
    private static Scene configScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;

            //FXMLLoader fxmlHome = new FXMLLoader(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/home.fxml"));
            Parent fxmlHome = FXMLLoader.load(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/home.fxml"));
            homeScene = new Scene(fxmlHome);

            Parent fxmlConfig = FXMLLoader.load(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/config.fxml"));
            configScene = new Scene(fxmlConfig);

            primaryStage.setTitle("My Application");
            primaryStage.setScene(homeScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeScreen(String scr){
        switch (scr){
            case "home":
                stage.setScene(homeScene);
                break;
            case "config":
                stage.setScene(configScene);
                break;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }


}
