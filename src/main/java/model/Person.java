package model;

public class Person {
    private String Username;
    private String Password;

    public Person(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
