package com.example.mooovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mooovies.FullFilm;
import com.example.mooovies.R;
import com.example.mooovies.models.Film;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.Holder> {

    List<Film> films;
    Context context;

    public FilmAdapter(List<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.film_item_default, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Film film = films.get(position);

        holder.tv_title.setText(film.getTitle());
        Picasso.get()
                .load(film.getCover())
                .into(holder.iv_cover);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView iv_cover;
        TextView tv_title;

        public Holder(@NonNull View itemView) {
            super(itemView);

            iv_cover = itemView.findViewById(R.id.iv_cover);
            tv_title = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    Film film = films.get(index);

                    Intent intent = new Intent(context, FullFilm.class);
                    intent.putExtra("film", film);
                    context.startActivity(intent);
                }
            });

        }
    }

}
