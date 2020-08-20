package com.example.mooovies.models;

public class User {
    String id;
    String username;
    String email;
    String age;

    public User(String id, String username, String email, String age) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }
}
