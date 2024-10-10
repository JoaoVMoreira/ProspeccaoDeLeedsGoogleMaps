package br.com.moreira.googleMapsLeeds;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.loader.Loader;

import java.io.IOException;
import java.security.Provider;

public class Main extends Application {
    private static Stage stage;
    private static Scene homeScene;
    private static Scene configScene;
    private static Scene mainDb;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;

            Parent fxmlHome = FXMLLoader.load(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/home.fxml"));
            homeScene = new Scene(fxmlHome);

            Parent fxmlConfig = FXMLLoader.load(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/config.fxml"));
            configScene = new Scene(fxmlConfig);

            Parent fxmlMainDb = FXMLLoader.load(getClass().getResource("/br/com/moreira/googleMapsLeeds/view/mainDb.fxml"));
            mainDb = new Scene(fxmlMainDb);

            primaryStage.setTitle("My Application");
            primaryStage.setResizable(false);
            primaryStage.setScene(homeScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeScreen(String scr){
        try{
            FXMLLoader loader;
            Parent root;
            switch (scr){
                case "home":
                    loader = new FXMLLoader(Main.class.getResource("/br/com/moreira/googleMapsLeeds/view/home.fxml"));
                    root = loader.load();
                    stage.setScene(new Scene(root));
                    break;
                case "config":
                    stage.setScene(configScene);
                    break;
                case "mainDb":
                    loader = new FXMLLoader(Main.class.getResource("/br/com/moreira/googleMapsLeeds/view/mainDb.fxml"));
                    root = loader.load();
                    stage.setScene(new Scene(root));
                    break;
                case "whatsApp":
                    loader = new FXMLLoader(Main.class.getResource("/br/com/moreira/googleMapsLeeds/view/whatsApp.fxml"));
                    root = loader.load();
                    stage.setScene(new Scene(root));
                    break;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }


}
