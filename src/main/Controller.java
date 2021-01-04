package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import kinopoisk.KinopoiskAPI;
import model.Movie;

public class Controller {
    KinopoiskAPI api = new KinopoiskAPI();

    @FXML
    public VBox layout;

    @FXML
    public StackPane contentPage;

    @FXML
    private TextField searchField;

    @FXML
    private BorderPane header;

    public void initialize() {
        initializeSearchField();
        showFilmPageById(3498);
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

        // creating black background
        contentPage.setStyle("-fx-background-color: #000000");

        // setting on film poster
        ImageView posterImage = new ImageView(poster);
        posterImage.setFitWidth(1080);
        posterImage.setEffect(new GaussianBlur());
        posterImage.setSmooth(true);
        posterImage.setPreserveRatio(true);
        posterImage.setViewport(new Rectangle2D(0, 0, posterImage.getFitWidth(), posterImage.getFitHeight()));

        contentPage.getChildren().addAll(posterImage);
        contentPage.setAlignment(Pos.TOP_CENTER);

        BorderPane contentBorder = new BorderPane();
        contentBorder.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-background-radiusx: 5px");
        contentBorder.setMinHeight(800);

        // setting on left side of content page
        VBox lContent = new VBox();
        lContent.getStyleClass().add("left-content-border");
        lContent.setPadding(new Insets(50, 30, 0, 50));

        // setting on preview image
        ImageView preview = new ImageView(new Image(movie.getPosterUrlPreview().toExternalForm()));
        preview.setFitWidth(240);
        preview.setFitHeight(520);
        preview.setPreserveRatio(true);
        preview.getStyleClass().add("preview-image");
        lContent.getChildren().add(preview);

        contentBorder.setLeft(lContent);

        ScrollPane contentScrollPane = new ScrollPane();
        contentScrollPane.setContent(contentBorder);
        contentScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        contentPage.getChildren().add(contentScrollPane);

        contentPage.setOnScroll(new EventHandler<>() {
            @Override
            public void handle(ScrollEvent event) {
                Rectangle2D vp = posterImage.getViewport();
                Rectangle2D newVp = new Rectangle2D(
                        vp.getMinX(), vp.getMinY() + event.getDeltaX(), vp.getWidth(), vp.getHeight());
                posterImage.setViewport(newVp);
            }
        });
    }
}
