package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
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
    }


    public static void main(String[] args) {
        launch(args);
    }
}
