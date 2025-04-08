package project.java.general;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;

//import javafx.project.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.event.Event;


//Classe che gestisce il cambio scena dell'applicazione
public class SceneController{

    //metodo per errori nel caricamento della scena
    public static void showControllerError(Exception e, String location){
        Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() +
            ": Errore nel caricamento della scena" +
            "\nPercorso scena: '" +
            location + "'" +
            "\nMessaggio di errore: " +
            e.getMessage());
    }

    //metodo per mostrare la finestra di alert
    public static void showAlertWindow(String title, String description){
        //finestra di alert informativo
        Alert successSignupAlert = new Alert(Alert.AlertType.INFORMATION);
        //stile dell'alert informativo
        successSignupAlert.setTitle(title);
        successSignupAlert.setHeaderText(null); //rimuovo il testo di intestazione
        successSignupAlert.setContentText(description);
        successSignupAlert.setGraphic(null); //rimuovo il grafico/icona che può accompagnare alert

        //applica stile presente nel file css
        DialogPane dialogPane = successSignupAlert.getDialogPane();
        dialogPane.getStylesheets().add(SceneController.class.getResource("/project/resources/project.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");

        //mostra alert e attende input dell'utente
        successSignupAlert.showAndWait();
    } 
    
    //metodo per caricare scena attraverso il file fxml
    public static void loadFXML(Event event, String location){
        try{
            Parent root = FXMLLoader.load(SceneController.class.getResource(location));
            switchScene((Node) event.getSource(), root); //cambia la scena attuale con la scena caricata
        }catch(IOException | RuntimeException e){
            SceneController.showControllerError(e, location);
        }
    }

    //metodo per cambiare scena
    public static void switchScene(Node source, Parent root){
        Stage stage = (Stage) source.getScene().getWindow();
        //crea una nuova scena con il parametro root
        Scene scene = new Scene(root);
        scene.getStylesheets().add(SceneController.class.getResource("/project/resources/project.css").toExternalForm());
        stage.setScene(scene); //imposta la nuova scena sullo stage
        Platform.runLater(() -> root.requestFocus()); //sposta il focus sulla scena
        stage.show(); //mostra la finestra aggiornata
    }
}