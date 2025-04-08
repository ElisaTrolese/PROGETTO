package project.java.dashboard;

public class UserScraper {
    
    private static String username = null;
    private static String name = null;
    private static String surname = null;

    public static void scraper(String username, String name, String surname){
        UserScraper.username = username;
        UserScraper.name = name;
        UserScraper.surname = surname;
    }

    public static String getUsername(){
        return username;
    }

    public static String getName(){
        return name;
    }

    public static String getSurname(){
        return surname;
    }

    public static void removeInfo(){
        username = null;
        name = null;
        surname = null;
    }
}

