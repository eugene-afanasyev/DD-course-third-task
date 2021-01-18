package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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
        showFilmPageById(301);
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


        // setting on center column content
        filmNameRu.setText(movie.getNameRu());
        filmNameEn.setText(movie.getNameEn() + "   +" + movie.getRatingAgeLimits());
        filmDescription.setText(movie.getDescription());

        generateInfoField("Год производства", movie.getYear());
        String countries = new String("");
        for (String country : movie.getCountries()) {
            if (countries.equals(""))
                countries = country;
            else
                countries += ", " + country;
        }
        generateInfoField("Страна", countries);

        String genres = new String("");
        for (String genre : movie.getGenres()) {
            if (genres.equals(""))
                genres = genre;
            else
                genres += ", " + genre;
        }
        generateInfoField("Жанры", genres);
        generateInfoField("Слоган", movie.getSlogan());
        generateInfoField("Премьера в Росcии", movie.getPremiereRu());
        generateInfoField("Премьера в мире", movie.getPremiereWorld());
        generateInfoField("Длительность", movie.getFilmLength());

        contentBorderPane.setLeft(leftColumn);
        contentBorderPane.setCenter(centerColumn);
        contentScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    private void generateInfoField(String propName, String propValue) {
        BorderPane pane = new BorderPane();
        Label propNameLabel = new Label(propName);
        Label propValueLabel = new Label(propValue);

        propNameLabel.getStyleClass().add("prop-name-label");
        propValueLabel.getStyleClass().add("prop-value-label");

        pane.setLeft(propNameLabel);
        pane.setRight(propValueLabel);

        centerColumn.getChildren().addAll(pane, new Separator());
    }
}
