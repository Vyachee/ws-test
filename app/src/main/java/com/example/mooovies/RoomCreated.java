package com.example.mooovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mooovies.adapters.FilmAdapter;
import com.example.mooovies.models.Film;

import java.util.List;

public class RoomCreated extends AppCompatActivity {

    RecyclerView rv_films;
    TextView tv_code;
    ImageView iv_search_button;

    class GetFilms extends AsyncTask<Void, Void, List<Film>> {

        @Override
        protected List<Film> doInBackground(Void... voids) {
            return new Api().getFilms(0);
        }

        @Override
        protected void onPostExecute(List<Film> films) {
            super.onPostExecute(films);
            GridLayoutManager manager = new GridLayoutManager(RoomCreated.this, 3);
            FilmAdapter adapter = new FilmAdapter(films, RoomCreated.this);

            rv_films.setAdapter(adapter);
            rv_films.setLayoutManager(manager);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_created);

        initViews();



    }

    public void initViews() {
        rv_films = findViewById(R.id.rv_films);
        tv_code = findViewById(R.id.tv_code);
        iv_search_button = findViewById(R.id.iv_search_button);
    }
}