package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Movie;

import java.util.HashMap;

public class Controller {
    private HashMap<Integer, Movie> movies;

    @FXML
    private TextField searchField;

    @FXML
    private BorderPane header;

    public void initialize() {
        initialzieSearchField();
    }

    public void initialzieSearchField() {
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
