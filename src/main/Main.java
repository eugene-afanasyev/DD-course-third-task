package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kinopoisk.KinopoiskAPI;

public class Main extends Application {
    KinopoiskAPI kinopoiskAPI = new KinopoiskAPI();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/mainView.fxml"));
        Scene mainScene = new Scene(root, 1280, 766);
        mainScene.setFill(null);
        mainScene.getStylesheets().add(getClass().getResource("/Stylesheets/style.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
