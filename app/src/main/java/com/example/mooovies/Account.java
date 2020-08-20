package com.example.mooovies;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Account extends Fragment {

    RecyclerView rv_fav_films;
    TextView tv_show_favs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);





        return v;
    }

    public void initViews(View v) {
        rv_fav_films = v.findViewById(R.id.rv_fav_films);
        tv_show_favs = v.findViewById(R.id.tv_show_favs);
    }
}