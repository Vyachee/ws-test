package com.example.mooovies;

import android.content.Context;
import android.content.SharedPreferences;

public class Cache {

    Context context;

    public Cache(Context context) {
        this.context = context;
    }

    public void writeToken(String token) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("token", token);

        editor.apply();
    }

    public String getToken() {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return preferences.getString("token", null);
    }

    public void removeToken () {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove("token");

        editor.apply();
    }

}
