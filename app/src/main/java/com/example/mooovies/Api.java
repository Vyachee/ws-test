package com.example.mooovies;

import com.example.mooovies.models.Film;
import com.example.mooovies.models.Review;
import com.example.mooovies.models.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Api {

    private String BASE_API_URL = "http://192.168.100.64/api/";

    public Api(String BASE_API_URL) {
        this.BASE_API_URL = BASE_API_URL;
    }

    public Api() {
    }

    public String getBASE_API_URL() {
        return BASE_API_URL;
    }

    public List<Film> getFilms(int offset) {

        Requests requests = new Requests();
        String response = requests.getRequest(BASE_API_URL + "getFilms?offset=" + offset);

        Gson gson = new Gson();

        try {
            List<Film> films = new ArrayList<>();

            JSONArray array = new JSONArray(response);

            for(int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Film film = gson.fromJson(object.toString(), Film.class);
                films.add(film);
            }


            return films;

        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String register(String username, String password, String email) {

        String request = BASE_API_URL + "register";
        String params = "username=" + username + "&password=" + password + "&age=0&email=" + email;

        Requests requests = new Requests();

        String response = requests.postRequest(request, params);

        try {

            JSONObject jsonObject = new JSONObject(response);
            String token = jsonObject.getString("token");

            return token;

        }   catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String auth(String email, String password) {

        String request = BASE_API_URL + "login";
        String data = "email=" + email + "&password=" + password;

        Requests requests = new Requests();

        String response = requests.getRequest(request + "?" + data);

        try {

            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.has("error")) return null;

            return jsonObject.getString("token");

        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public User getUserInfo(String token) {
        String request = BASE_API_URL + "getUserInfo?token=" + token;

        Requests requests = new Requests();
        String response = requests.getRequest(request);
        Gson gson = new Gson();
        User user = gson.fromJson(response, User.class);
        return user;
    }

    public String createReview(String rate, String film_id, String user_id, String text, String token) {
        String request = BASE_API_URL + "createReview";
        String params = "rate=" + rate + "&film_id=" + film_id + "&user_id=" + user_id + "&text=" + text + "&token=" + token;

        request += "?" + params;

        String response = new Requests().postRequest(request, params);

        return response;
    }

    public List<Review> getReviews(String film_id) {

        String request = BASE_API_URL + "getReviews?film_id=" + film_id;
        String response = new Requests().getRequest(request);

        try {

            List<Review> reviews = new ArrayList<>();
            JSONArray array = new JSONArray(response);
            Gson gson = new Gson();

            for(int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Review review = gson.fromJson(object.toString(), Review.class);
                reviews.add(review);
            }

            return reviews;

        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
