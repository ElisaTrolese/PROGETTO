package project.java.login;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//classe per la gestione delle segnalazioni di errore durante accesso e registrazione
public class GraphicalAnswer {

    //metodo per controllare se i TextField sono vuoti
    public static ArrayList<TextField> missingData(ActionEvent event, ArrayList<TextField> data){
        ArrayList<TextField> empty = new ArrayList<TextField>();

        for(TextField d : data){
            if(d.getText().isEmpty()){
                empty.add(d);
            }else{
                d.setStyle(""); //ripristina allo stato normale il bordo se il TextField è pieno
            }
        }
        return empty;
    }

    //metodo per segnalare errore nell'inserimento di dati
    public static void alertMessage(ArrayList<TextField> data, Label messageLabel, String message){
        
        for(TextField d : data){
            d.setStyle("-fx-border-color: #bf616a; -fx-border-width: 2;");
        }

        messageLabel.setText(message); //scrive messaggio di errore nella Lebal indicata
    }
    
}
