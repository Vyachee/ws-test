package com.example.mooovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mooovies.adapters.ReviewAdapter;
import com.example.mooovies.models.Film;
import com.example.mooovies.models.Review;
import com.example.mooovies.models.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FullFilm extends AppCompatActivity {

    TextView tv_title;
    TextView tv_year;
    TextView tv_country;
    TextView tv_genre;
    TextView tv_prod;
    TextView tv_watch_together;
    TextView tv_description;

    TextView b_send_review;

    VideoView vv_trailer;
    VideoView vv_film;

    LinearLayout ll_like;
    LinearLayout ll_save;
    LinearLayout ll_vv_bg;
    LinearLayout ll_main;
    LinearLayout ll_my_stars;

    EditText et_review_text;

    ImageView iv_star_1;
    ImageView iv_star_2;
    ImageView iv_star_3;
    ImageView iv_star_4;
    ImageView iv_star_5;
    ImageView iv_star_6;
    ImageView iv_star_7;
    ImageView iv_star_8;
    ImageView iv_star_9;
    ImageView iv_star_10;
    ImageView iv_cover;
    ImageView iv_play_trailer;
    ImageView iv_play_film;

    RecyclerView rv_reviews;

    int rate;
    List<ImageView> stars = new ArrayList<>();
    List<Review> reviewList = new ArrayList<>();
    String token;

    Bundle extras;

    Film film;
    String user_id;
    User userInfo;

    class GetUserInfo extends AsyncTask<String, Void, User> {

        @Override
        protected User doInBackground(String... strings) {
            Api api = new Api();
            User user = api.getUserInfo(token);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            user_id = user.getId();
            userInfo = user;

            new GetReviews().execute(film.getId());
        }
    }

    class SendReview extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return new Api().createReview(strings[0], strings[1], strings[2], strings[3], strings[4]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            Review review = gson.fromJson(s, Review.class);

            ((ReviewAdapter) rv_reviews.getAdapter()).addMyReview(review);


        }
    }

    class GetReviews extends AsyncTask<String, Void, List<Review>> {

        @Override
        protected List<Review> doInBackground(String... strings) {
            return new Api().getReviews(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Review> reviews) {
            super.onPostExecute(reviews);

            reviewList.addAll(reviews);

            boolean myReviewAdded = false;

            int i = 0;

            for(Review r : reviewList) {
                if(r.getUser_id().equals(user_id)) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
                    ll_my_stars.setLayoutParams(params);
                    et_review_text.setLayoutParams(params);
                    b_send_review.setLayoutParams(params);

                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params1.setMargins(64, 24, 64, 24);

                    if(!myReviewAdded) {

                        TextView tv = new TextView(FullFilm.this);
                        tv.setText("Ваши отзывы");
                        tv.setLayoutParams(params1);
                        tv.setTextAppearance(FullFilm.this, R.style.fonts);

                        ll_main.addView(tv);
                        myReviewAdded = true;

                    }


                    LayoutInflater inflater = LayoutInflater.from(FullFilm.this);
                    View v = inflater.inflate(R.layout.review_item, null, false);
                    TextView name = v.findViewById(R.id.tv_name);
                    TextView text = v.findViewById(R.id.tv_text);
                    TextView date = v.findViewById(R.id.tv_date);

                    ImageView iv_star_1 = v.findViewById(R.id.iv_star_1);
                    ImageView iv_star_2 = v.findViewById(R.id.iv_star_2);
                    ImageView iv_star_3 = v.findViewById(R.id.iv_star_3);
                    ImageView iv_star_4 = v.findViewById(R.id.iv_star_4);
                    ImageView iv_star_5 = v.findViewById(R.id.iv_star_5);
                    ImageView iv_star_6 = v.findViewById(R.id.iv_star_6);
                    ImageView iv_star_7 = v.findViewById(R.id.iv_star_7);
                    ImageView iv_star_8 = v.findViewById(R.id.iv_star_8);
                    ImageView iv_star_9 = v.findViewById(R.id.iv_star_9);
                    ImageView iv_star_10 = v.findViewById(R.id.iv_star_10);

                    List<ImageView> stars = new ArrayList<>();
                    stars.add(iv_star_1);
                    stars.add(iv_star_2);
                    stars.add(iv_star_3);
                    stars.add(iv_star_4);
                    stars.add(iv_star_5);
                    stars.add(iv_star_6);
                    stars.add(iv_star_7);
                    stars.add(iv_star_8);
                    stars.add(iv_star_9);
                    stars.add(iv_star_10);

                    for(int u = 0; u < r.getRate(); u++) {
                        ImageView star = stars.get(u);
                        star.setImageTintList(getResources().getColorStateList(R.color.lightgray));
                    }

                    name.setText(userInfo.getUsername());
                    text.setText(r.getText());
                    date.setText(r.getDate());

                    v.setLayoutParams(params1);

                    ll_main.addView(v);

                    reviews.remove(i);

                }
            }

            LinearLayoutManager manager = new LinearLayoutManager(FullFilm.this);
            ReviewAdapter adapter = new ReviewAdapter(reviews, FullFilm.this);

            rv_reviews.setAdapter(adapter);
            rv_reviews.setLayoutManager(manager);
            i++;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_film);
        initViews();

        extras = getIntent().getExtras();
        if(extras != null) {
            film = (Film) extras.getSerializable("film");
        }


        Cache cache = new Cache(FullFilm.this);
        token = cache.getToken();

        new GetUserInfo().execute(token);

        b_send_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = et_review_text.getText().toString();
                new SendReview().execute(String.valueOf(rate), film.getId(), user_id, text, token);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
                ll_my_stars.setLayoutParams(params);
                et_review_text.setLayoutParams(params);
                b_send_review.setLayoutParams(params);
            }
        });

        tv_title.setText(film.getTitle());
        Picasso.get().load(film.getCover()).into(iv_cover);

        tv_description.setText(film.getDescription());
        tv_country.setText(film.getCountry());
        tv_genre.setText(film.getGenre());
        tv_prod.setText(film.getProd());
        tv_year.setText(film.getYear());

        vv_trailer.setVideoURI(Uri.parse(film.getTrailer()));

        vv_trailer.start();
        vv_trailer.pause();

        iv_play_trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vv_trailer.isPlaying()) {
                    vv_trailer.pause();
                    iv_play_trailer.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_solid));

                } else {
                    vv_trailer.start();
                    iv_play_trailer.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_solid));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_play_trailer.animate().alpha(0).setDuration(100);
                        }
                    }, 500);
                }

            }
        });

        ll_vv_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_play_trailer.animate().alpha(1).setDuration(100);
            }
        });


    }

    public void selectRate(View view) {
        int needle = 0;

        switch (view.getId()) {
            case R.id.iv_star_1:
                needle = 1;
                break;
            case R.id.iv_star_2:
                needle = 2;
                break;
            case R.id.iv_star_3:
                needle = 3;
                break;
            case R.id.iv_star_4:
                needle = 4;
                break;
            case R.id.iv_star_5:
                needle = 5;
                break;
            case R.id.iv_star_6:
                needle = 6;
                break;
            case R.id.iv_star_7:
                needle = 7;
                break;
            case R.id.iv_star_8:
                needle = 8;
                break;
            case R.id.iv_star_9:
                needle = 9;
                break;
            case R.id.iv_star_10:
                needle = 10;
                break;
        }

        rate = needle;


        // Clear all stars
        for(int i = 0; i < stars.size(); i++)
            stars.get(i).setImageTintList(getResources().getColorStateList(R.color.navbar_color));

        // Fill needle count of stars
        for(int i = 0; i < needle; i++) {
            stars.get(i).setImageTintList(getResources().getColorStateList(R.color.lightgray));
        }
    }

    public void initViews() {
        tv_title = findViewById(R.id.tv_title);
        tv_year = findViewById(R.id.tv_year);
        tv_country = findViewById(R.id.tv_country);
        tv_genre = findViewById(R.id.tv_genre);
        tv_prod = findViewById(R.id.tv_prod);
        tv_watch_together = findViewById(R.id.tv_watch_together);
        tv_description = findViewById(R.id.tv_description);
        b_send_review = findViewById(R.id.b_send_review);

        vv_trailer = findViewById(R.id.vv_trailer);
        vv_film = findViewById(R.id.vv_film);

        ll_like = findViewById(R.id.ll_like);
        ll_save = findViewById(R.id.ll_save);
        ll_vv_bg = findViewById(R.id.ll_vv_bg);
        ll_my_stars = findViewById(R.id.ll_my_stars);
        ll_main = findViewById(R.id.ll_main);

        et_review_text = findViewById(R.id.et_review_text);

        iv_star_1 = findViewById(R.id.iv_star_1);
        iv_star_2 = findViewById(R.id.iv_star_2);
        iv_star_3 = findViewById(R.id.iv_star_3);
        iv_star_4 = findViewById(R.id.iv_star_4);
        iv_star_5 = findViewById(R.id.iv_star_5);
        iv_star_6 = findViewById(R.id.iv_star_6);
        iv_star_7 = findViewById(R.id.iv_star_7);
        iv_star_8 = findViewById(R.id.iv_star_8);
        iv_star_9 = findViewById(R.id.iv_star_9);
        iv_star_10 = findViewById(R.id.iv_star_10);

        stars.add(iv_star_1);
        stars.add(iv_star_2);
        stars.add(iv_star_3);
        stars.add(iv_star_4);
        stars.add(iv_star_5);
        stars.add(iv_star_6);
        stars.add(iv_star_7);
        stars.add(iv_star_8);
        stars.add(iv_star_9);
        stars.add(iv_star_10);

        iv_cover = findViewById(R.id.iv_cover);
        iv_play_trailer = findViewById(R.id.iv_play_trailer);
        iv_play_film = findViewById(R.id.iv_play_film);

        rv_reviews = findViewById(R.id.rv_reviews);
    }

}