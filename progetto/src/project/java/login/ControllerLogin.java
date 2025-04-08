package project.java.login;

import java.io.*;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

//import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.java.general.SceneController;
import project.java.dashboard.ControllerDashboard;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;


public class ControllerLogin{

    private static final String JSON_FILE = "data/user.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @FXML 
    private TextField usernameSignup;

    @FXML
    private TextField passwordSignup;

    @FXML
	private TextField nameSignup;
	
	@FXML
	private TextField surnameSignup;
	
	@FXML
	private TextField usernameLogin;
	
	@FXML
	private TextField passwordLogin;
	
	@FXML
	private Label errorMessageLogin;
	
	@FXML
	private Label errorMessageSignup;
    
    //passaggio alla scena Login
    public void switchToLoginScene(ActionEvent event){
        SceneController.loadFXML(event, "/project/resources/login.fxml");
    }

    //passaggio alla scena Registrazione
    public void switchToSignupScene(ActionEvent event){
        SceneController.loadFXML(event, "/project/resources/signup.fxml");
    }

    /*//passaggio alla scena dashboard
    public void switchToDashboardScene(ActionEvent event){
        String location = "/project/resources/dashboard.fxml";

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            Parent dashboardRoot = loader.load();

            ControllerDashboard cd = loader.getController();
            cd.updateDashboardData();
            cd.setWelcomeText();

            SceneController.switchScene((Node) event.getSource(), dashboardRoot);
        }catch(IOException | RuntimeException e){
            SceneController.showControllerError(e, location);
        }
    }*/

    //gestione del login
    public void login(ActionEvent event){
        String username = usernameLogin.getText();
        String password = passwordLogin.getText();
        
        if(username.isEmpty() || password.isEmpty()){
            errorMessageLogin.setText("Dati mancanti");
            return;
        }
        if(checkLogin(username, password)){
            SceneController.showAlertWindow("Login riuscito", "Benvenuto," + username);
        }else{
            errorMessageLogin.setText("Credenziali non valide");
        }

        /*if(checkLogin(username, password)){
            switchToDashboardScene(event);
        }else{
            errorMessageLogin.setText("Credenziali non valide");
        }*/
    }

    //gestione del signup
    public void signup(ActionEvent event){
        String username = usernameSignup.getText();
        String password = passwordSignup.getText();
        String name = nameSignup.getText();
        String surname = surnameSignup.getText();
        
        if(username.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()){
            errorMessageLogin.setText("Dati mancanti");
            return;
        }

        if(addUser(username, password, name, surname)){
            switchToLoginScene(event);
            SceneController.showAlertWindow("Registrazione avvenuta con successo!", "Ora puoi procedere con il login");
        }else{
            errorMessageLogin.setText("Credenziali già esistenti");
        }
    }

    private boolean checkLogin(String username, String password){
        List<User> users = loadUsers();
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    private boolean addUser(String username, String password, String name, String surname){
        List<User> users = loadUsers();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return false;
            }
        }
        users.add(new User(username, password, name, surname));
        saveUsers(users);
        return true;
        
    }

    private List<User> loadUsers(){
        try{
            File file = new File(JSON_FILE);
            if(!file.exists()){
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<User>>(){});
        }catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveUsers(List<User> users) {
        try{
            objectMapper.writeValue(new File(JSON_FILE), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




