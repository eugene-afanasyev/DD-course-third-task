package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kinopoisk.KinopoiskAPI;
import model.Movie;

import java.awt.*;
import java.util.HashMap;

public class Controller {
    KinopoiskAPI api = new KinopoiskAPI();

    @FXML
    public VBox contentVBox;

    @FXML
    private TextField searchField;

    @FXML
    private BorderPane header;

    public void initialize() {
        initializeSearchField();
    }

    public void initializeSearchField() {
        searchField.setFocusTraversable(false);

        searchField.focusedProperty().addListener(new ChangeListener<Boolean>() {
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
}
