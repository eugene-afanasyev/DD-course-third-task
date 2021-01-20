package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kinopoisk.KinopoiskAPI;
import model.Movie;

import java.io.IOException;
import java.util.ArrayList;

public class HeaderController {
    @FXML
    public TextField searchField;
    @FXML
    public BorderPane header;
    @FXML
    public Button searchButton;

    public void initialize() {
        searchField.setFocusTraversable(false);

        searchField.focusedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                searchField.getStyleClass().clear();
                if (t1) {
                    searchField.getStyleClass().add("searchFieldFocused");
                } else {
                    searchField.getStyleClass().add("searchField");
                }
            }
        });
    }

    public void load(int id) {
        FilmPageController.filmId = id;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/filmPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) header.getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("/Stylesheets/style.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(MouseEvent event) {
        KinopoiskAPI api = new KinopoiskAPI();
        ArrayList<Movie> films = api.getMoviesByKeyword(searchField.getText());
    }
}
