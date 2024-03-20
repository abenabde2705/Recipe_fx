package Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// HelloApplication.java
public class CookingApp extends Application {
    private Stage primaryStage;
    private Scene textScene;
    private Scene graphicalScene;
    private HelloController helloController;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        helloController = loader.getController();
        helloController.setMainApp(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cooking App");
        primaryStage.show();

    }
    public void showGraphicalView(Stage primaryStag) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("graphical-view.fxml"));
            Parent root = loader.load();
            Scene graphicalScene = new Scene(root);
            primaryStage.setScene(graphicalScene);
            primaryStage.setTitle("Graphical Presentation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}