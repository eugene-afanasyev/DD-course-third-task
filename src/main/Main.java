package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kinopoisk.KinopoiskAPI;
import model.Movie;
import org.apache.http.HttpHeaders;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.util.List;

public class Main extends Application {
    KinopoiskAPI kinopoiskAPI = new KinopoiskAPI();

    private Stage mainStage;
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml"));
        mainScene = new Scene(root, 1280, 766);
        mainScene.getStylesheets().add(getClass().getResource("/Stylesheets/style.css").toExternalForm());

        mainStage.setScene(mainScene);
        mainStage.show();

        List <Movie> movies = kinopoiskAPI.getMovieByKeywords("Matrix");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
