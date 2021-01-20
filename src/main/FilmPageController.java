package main;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import kinopoisk.KinopoiskAPI;
import model.Movie;
import model.Staff;

import java.util.ArrayList;


public class FilmPageController {
    @FXML
    public VBox layout;
    @FXML
    public StackPane contentWrapper;
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
    public VBox rightColumn;

    public static int filmId = 301;


    public void initialize() {
        showFilmPageById(filmId);
    }

    public void showFilmPageById(int id)  {
        KinopoiskAPI api = new KinopoiskAPI();
        Movie movie = api.getMovieById(id);
        Image poster;
        if (movie.getPosterUrl() != null) {
            poster = new Image(movie.getPosterUrl().toExternalForm());
        } else {
            poster = null;
        }

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

        // setting on film frames
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxHeight(300);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        HBox hbox = new HBox();
        hbox.setPrefWidth(centerColumn.getMinWidth());
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: #c5cffc");
        hbox.setPadding(new Insets(15, 5, 15, 5));
        scrollPane.setContent(hbox);

        for (Image image : api.getFramesById(movie.getFilmId())) {
            ImageView img = new ImageView(image);
            img.setStyle("-fx-border-color: white; -fx-border-width: 3; -fx-border-radius: 3");
            img.setPreserveRatio(true);
            img.setFitHeight(300);
            hbox.getChildren().add(img);
        }
        centerColumn.getChildren().add(scrollPane);

        // setting on right column content
        ArrayList<Staff> actors = api.getActorsByFilmId(movie.getFilmId());
        for (Staff actor : actors) {
            Label l = new Label(actor.getNameRu());
            l.setStyle("-fx-wrap-text: true; -fx-font-size: 22; -fx-text-fill: #c9c9c9");
            rightColumn.getChildren().add(l);
        }

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
