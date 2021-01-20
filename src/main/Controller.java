package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kinopoisk.KinopoiskAPI;
import model.Movie;
import model.Actor;
import model.Staff;

import java.io.IOException;
import java.util.ArrayList;

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
    private TextField searchField;

    public BorderPane contentBorderPane = new BorderPane();
    public VBox leftColumn = new VBox();
    public VBox centerColumn = new VBox();
    public VBox rightColumn = new VBox();

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

        contentBorderPane.prefWidth(1024);
        showFilmPageById(301);
    }

    public void search(MouseEvent event) {
        KinopoiskAPI api = new KinopoiskAPI();
        ArrayList<Movie> films = api.getMoviesByKeyword(searchField.getText());

        VBox vbox = new VBox();
        for (Movie film : films) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(15);
            hBox.setStyle("-fx-padding: 5");

            ImageView imageView =
                    new ImageView(new Image(film.getPosterUrlPreview().toExternalForm()));
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);

            Label label = new Label(film.getNameRu());
            label.setStyle("-fx-font-family: 'Open Sans'; -fx-font-size: 30;");

            hBox.getChildren().addAll(imageView, label);
            vbox.getChildren().add(hBox);
        }
        contentScrollPane.setContent(vbox);
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
        ImageView preview = new ImageView();
        preview.setImage(new Image(movie.getPosterUrlPreview().toExternalForm()));
        preview.setFitWidth(250);
        preview.setPreserveRatio(true);
        leftColumn.getChildren().add(preview);
        leftColumn.setStyle("-fx-padding: 10");

        // setting on center column content
        Label filmNameRu = new Label(movie.getNameRu());
        filmNameRu.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 40; -fx-font-weight: bold");
        filmNameRu.setWrapText(true);

        Label filmNameEn = new Label(movie.getNameEn() + "   +" + movie.getRatingAgeLimits());
        filmNameEn.setStyle("-fx-text-fill: #868686; -fx-font-size: 28;");
        filmNameEn.setWrapText(true);

        Label filmDescription = new Label(movie.getDescription());
        filmDescription.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 18");
        filmDescription.setWrapText(true);

        Label aboutFilmLabelTitle = new Label("О фильме");
        aboutFilmLabelTitle.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 30;");
        filmDescription.setWrapText(true);

        centerColumn.getChildren().addAll(filmNameRu, filmNameEn, filmDescription, aboutFilmLabelTitle);

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

        Label framesLibelTitle = new Label("Кадры: ");
        framesLibelTitle.setStyle("-fx-text-fill: white; -fx-font-size: 28");

        centerColumn.getChildren().add(framesLibelTitle);
        centerColumn.getChildren().add(scrollPane);

        centerColumn.setSpacing(10);
        centerColumn.setStyle("-fx-padding: 10");
        centerColumn.setPrefWidth(700);

        Label actorsLabelTitle = new Label("В главных ролях: ");
        actorsLabelTitle.setStyle("-fx-text-fill: white; -fx-font-size: 30");
        rightColumn.getChildren().add(actorsLabelTitle);

        // setting on right column content
        ArrayList<Staff> actors = api.getActorsByFilmId(movie.getFilmId());
        int counter = 0;
        for (Staff actor : actors) {
            if (counter++ == 10)
                break;

            Label l = new Label(actor.getNameRu());
            l.setStyle("-fx-wrap-text: true; -fx-font-size: 22; -fx-text-fill: #ffffff");
            l.hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (l.isHover()) {
                        l.setTextFill(Color.DARKORANGE);
                    } else {
                        l.setTextFill(Color.WHITE);
                    }
                }
            });

            rightColumn.getChildren().add(l);
        }

        contentBorderPane.setLeft(leftColumn);
        contentBorderPane.setRight(rightColumn);
        contentBorderPane.setCenter(centerColumn);
        contentScrollPane.setContent(contentBorderPane);
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
