package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kinopoisk.KinopoiskAPI;
import model.Actor;
import model.Movie;
import org.apache.http.HttpHeaders;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.util.List;

public class Main extends Application {
    KinopoiskAPI kinopoiskAPI = new KinopoiskAPI();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml"));
        Scene mainScene = new Scene(root, 1280, 766);
        mainScene.getStylesheets().add(getClass().getResource("/Stylesheets/style.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
