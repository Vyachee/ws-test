package com.example.mooovies.models;

import java.io.Serializable;

public class Film implements Serializable {

    String id;
    String title;
    String cover;
    String trailer;
    String description;
    String year;
    String country;
    String prod;
    String genre;

    public Film(String id, String title, String cover, String trailer, String description, String year, String country, String prod, String genre) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.trailer = trailer;
        this.description = description;
        this.year = year;
        this.country = country;
        this.prod = prod;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getDescription() {
        return description;
    }

    public String getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getProd() {
        return prod;
    }

    public String getGenre() {
        return genre;
    }
}
