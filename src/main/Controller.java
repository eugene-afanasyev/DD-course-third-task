package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import kinopoisk.KinopoiskAPI;
import model.FilmIdName;
import model.Movie;
import model.Actor;
import model.Staff;

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

    public BorderPane contentBorderPane;
    public VBox leftColumn;
    public VBox centerColumn;
    public VBox rightColumn;

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

        showActorPageById(513);
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

    public void showActorPageById(int id) {
        contentBorderPane = new BorderPane();
        leftColumn = new VBox();
        centerColumn = new VBox();
        rightColumn = new VBox();

        contentBorderPane.prefWidth(1024);

        KinopoiskAPI api = new KinopoiskAPI();
        Actor actor  = api.getActorById(id);

        Image poster;
        if (actor.getPosterURL() != null) {
            poster = new Image(actor.getPosterURL().toExternalForm());
        } else {
            poster = null;
        }

        ImageView preview = new ImageView(poster);
        preview.setFitWidth(300);
        preview.setPreserveRatio(true);
        leftColumn.getChildren().add(preview);
        leftColumn.setStyle("-fx-padding: 10");

        ImageView bg = new ImageView(poster);
        bg.setFitHeight(600);
        bg.setEffect(new MotionBlur());
        bg.setPreserveRatio(true);
        contentBackground.setAlignment(Pos.TOP_CENTER);
        contentBackground.getChildren().add(bg);

        leftColumn.setStyle("-fx-padding: 10");

        Label actorNameRu = new Label(actor.getNameRu());
        actorNameRu.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 40; -fx-font-weight: bold");

        Label actorNameEn = new Label(actor.getNameEn());
        actorNameEn.setStyle("-fx-text-fill: #868686; -fx-font-size: 28;");

        Label aboutActorLabelTitle = new Label("О персоне");
        aboutActorLabelTitle.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 30;");

        centerColumn.getChildren().addAll(actorNameRu, actorNameEn, aboutActorLabelTitle);

        centerColumn.setSpacing(10);
        centerColumn.setStyle("-fx-padding: 30");
        centerColumn.setMinWidth(600);
        centerColumn.setPrefWidth(700);

        generateInfoField("Карьера", actor.getProfession());
        generateInfoField("Рост", Integer.toString(actor.getGrowth()));
        generateInfoField("Дата рождения", actor.getBirthday());
        generateInfoField("Место рождения", actor.getBirthplace() + " - " + actor.getAge() + " y.o.");
        generateInfoField("Всего фильмов", Integer.toString(actor.getFilmsId().size()));

        Label someFilmsTitle = new Label("Фильмы");
        someFilmsTitle.setStyle("-fx-text-fill: white; -fx-font-size: 24");
        rightColumn.getChildren().add(someFilmsTitle);

        int counter = 0;
        for (FilmIdName filmIdName : actor.getFilmsId()) {
            if (filmIdName.getNameRu().equals(""))
                continue;
            else if (counter++ == 10)
                break;

            Label l = new Label(filmIdName.getNameRu());

            l.setStyle("-fx-wrap-text: true; -fx-font-size: 18; -fx-text-fill: #ffffff");
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

    public void showFilmPageById(int id)  {
        contentBorderPane = new BorderPane();
        leftColumn = new VBox();
        centerColumn = new VBox();
        rightColumn = new VBox();

        contentBorderPane.prefWidth(1080);

        KinopoiskAPI api = new KinopoiskAPI();
        Movie movie = api.getMovieById(id);
        Image poster;
        if (movie.getPosterUrl() != null) {
            poster = new Image(movie.getPosterUrl().toExternalForm());
        } else {
            poster = null;
        }

        ImageView bg = new ImageView(poster);
        bg.setEffect(new MotionBlur());
        bg.setPreserveRatio(true);
        bg.setFitHeight(600);
        contentBackground.getChildren().add(bg);
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
        hbox.setStyle("-fx-background-color: #1d1e40");
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
        actorsLabelTitle.setStyle("-fx-text-fill: white; -fx-font-size: 24");
        rightColumn.getChildren().addAll(actorsLabelTitle, new Separator());

        // setting on right column content
        ArrayList<Staff> actors = api.getStaffByFilmId(movie.getFilmId());
        int counter = 0;
        for (Staff actor : actors) {
            if (counter++ == 10)
                break;

            Label l = new Label(actor.getNameRu());
            l.setStyle("-fx-wrap-text: true; -fx-font-size: 18; -fx-text-fill: #ffffff");
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
