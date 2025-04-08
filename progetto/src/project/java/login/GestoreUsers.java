package project.java.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;

public class GestoreUsers {
    private static final String USER_FILE = "data/user.json";
    private static List<User> users = new ArrayList<>();

    static{
        loadUsers();
    }

    public static boolean authenticate(String username, String password){
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public static boolean userExists(String username){
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public static void registerUser(String username, String password, String name, String surname){
        User newUser = new User(username, password, name, surname);

        users.add(newUser);
        saveUsers();
    }

    //caricare i dati degli utenti da un file JSON e salvarli nella lista users
    private static void loadUsers() {
        try {
            File file = new File(USER_FILE);
            if (file.exists()) {
                //oggetto della libreria Jackson, che viene usato per convertire dati tra formati Java (oggetti) e JSON (stringhe)
                ObjectMapper mapper = new ObjectMapper();
                User[] usersArray = mapper.readValue(file, User[].class);
                users = new ArrayList<>(Arrays.asList(usersArray));  // Usare ArrayList per rendere la lista modificabile
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private static void saveUsers(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(USER_FILE), users);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
