package project.java.general;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxmlLocation = getClass().getResource("/project/resources/enterScene.fxml");
            if (fxmlLocation == null) {
                System.out.println("FXML file not found.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/project/resources/project.css").toExternalForm());
            
            primaryStage.setResizable(false); // La finestra non può essere ridimensionata
            primaryStage.setWidth(800); // Imposta la larghezza fissa della finestra
            primaryStage.setHeight(450); // Imposta l'altezza fissa della finestra
            primaryStage.setScene(scene); // Imposta la scena
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

