package com.example.mooovies;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mooovies.adapters.FilmAdapter;
import com.example.mooovies.models.Film;

import java.util.List;

public class AllFilms extends Fragment {

    public static AllFilms newInstance() {

        Bundle args = new Bundle();

        AllFilms fragment = new AllFilms();
        fragment.setArguments(args);
        return fragment;
    }

    NestedScrollView nestedScrollView;
    RecyclerView rv_films;

    class GetFilms extends AsyncTask<Void, Void, List<Film>> {

        @Override
        protected List<Film> doInBackground(Void... voids) {
            Api api = new Api();
            return api.getFilms(0);
        }

        @Override
        protected void onPostExecute(List<Film> films) {
            super.onPostExecute(films);
            GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
            FilmAdapter adapter = new FilmAdapter(films, getContext());

            rv_films.setAdapter(adapter);
            rv_films.setLayoutManager(manager);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_all_films, container, false);


        initViews(v);

        new GetFilms().execute();

        return v;
    }

    public void initViews(View v) {
        nestedScrollView = v.findViewById(R.id.nesterScrollView);
        rv_films = v.findViewById(R.id.rv_films);
    }
}