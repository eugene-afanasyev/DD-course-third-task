package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kinopoisk.KinopoiskAPI;
import model.Movie;

public class Controller {
    @FXML
    public StackPane contentWrapper;
    @FXML
    public VBox layout;
    @FXML
    public VBox contentBackground;
    @FXML
    public ScrollPane contentScrollPane;
    @FXML
    public BorderPane contentBorderPane;
    @FXML
    public VBox leftColumn;
    @FXML
    public VBox centerColumn;
    @FXML
    public Label filmNameRu;
    @FXML
    public Label filmNameEn;
    @FXML
    public Label filmDescription;
    @FXML
    private TextField searchField;
    @FXML
    private BorderPane header;

    KinopoiskAPI api = new KinopoiskAPI();

    public void initialize() {
        initializeSearchField();
        showFilmPageById(3491);
    }

    public void initializeSearchField() {
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

    public void showFilmPageById(int id)  {
        Movie movie = api.getMovieById(id);
        Image poster;
        if (movie.getPosterUrl() != null) {
            poster = new Image(movie.getPosterUrl().toExternalForm());
        } else {
            poster = null;
        }
        assert poster != null;

        BackgroundImage movieBg= new BackgroundImage(
                poster, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0, false, Side.TOP, 0, false),
                new BackgroundSize(contentWrapper.getPrefWidth(), contentWrapper.getPrefHeight(),
                        false, false, false, true));

        contentBackground.setBackground(new Background(movieBg));
//        contentBackground.setEffect(new GaussianBlur());

        // setting on left column content
        BackgroundImage moviePreviewBg = new BackgroundImage(
                new Image(movie.getPosterUrlPreview().toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0, false, Side.TOP, 0, false),
                new BackgroundSize(contentWrapper.getPrefWidth(), contentWrapper.getPrefHeight(),
                        false, false, false, true));
        leftColumn.setBackground(new Background(moviePreviewBg));
        // setting on left column content
        filmNameRu.setText(movie.getNameRu());
        filmNameEn.setText(movie.getNameEn());
        filmDescription.setText(movie.getDescription());

        contentBorderPane.setLeft(leftColumn);
        contentBorderPane.setCenter(centerColumn);
        contentScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }
}
