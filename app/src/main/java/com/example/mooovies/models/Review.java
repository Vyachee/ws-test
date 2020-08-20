package com.example.mooovies.models;

public class Review {
    String id;
    String user_id;
    String film_id;
    String username;
    String date;
    int rate;
    String text;

    public Review(String id, String user_id, String film_id, String username, String date, int rate, String text) {
        this.id = id;
        this.user_id = user_id;
        this.film_id = film_id;
        this.username = username;
        this.date = date;
        this.rate = rate;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getFilm_id() {
        return film_id;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public int getRate() {
        return rate;
    }

    public String getText() {
        return text;
    }
}
