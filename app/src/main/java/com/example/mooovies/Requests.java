package com.example.mooovies;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Requests {

    public String getRequest(String url) {
        try {
            URL rUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) rUrl.openConnection();
            InputStream is = conn.getInputStream();
            Scanner s = new Scanner(is);
            s.useDelimiter("\\A");

            if(s.hasNext()) return s.next();
            else return null;

        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String postRequest(String url, String data) {
        try {
            URL rUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) rUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            conn.getOutputStream().write(data.getBytes());

            InputStream is = conn.getInputStream();
            Scanner s = new Scanner(is);
            s.useDelimiter("\\A");

            if(s.hasNext()) return s.next();
            else return null;

        }   catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
