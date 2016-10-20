package com.company;

/**
 * Created by VeryBarry on 10/19/16.
 */
public class User {
    int id;
    String username;
    String password;

    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public int getId() { return id; }
    public String getEmail() { return username; }
    public String getPassword() { return password; }
}
