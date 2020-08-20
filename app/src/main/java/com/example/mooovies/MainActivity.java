package com.example.mooovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.mooovies.adapters.FilmAdapter;
import com.example.mooovies.models.Film;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    public void initViews() {
        viewPager = findViewById(R.id.viewPager);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new AllFilms();
                case 1: return new Search();
                case 2:
                    Cache cache = new Cache(MainActivity.this);
                    if(cache.getToken() != null) {
                        return new Account();
                    }   else return new Register();
            }
            return new Search();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
