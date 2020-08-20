package com.example.mooovies.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mooovies.R;
import com.example.mooovies.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Holder> {

    List<Review> reviews;
    Context context;

    public ReviewAdapter(List<Review> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }


    public void addMyReview(Review review) {
        reviews.add(review);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.review_item, parent, false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Review review = reviews.get(position);

        holder.tv_date.setText(review.getDate());
        holder.tv_name.setText(review.getUsername());
        holder.tv_text.setText(review.getText());

        for(int i = 0; i < review.getRate(); i++)
            holder.stars.get(i).setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.lightgray)));

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }



    class Holder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_date;
        TextView tv_text;

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

        List<ImageView> stars = new ArrayList<>();

        public Holder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_text = itemView.findViewById(R.id.tv_text);

            iv_star_1  = itemView.findViewById(R.id.iv_star_1);
            iv_star_2 = itemView.findViewById(R.id.iv_star_2);
            iv_star_3 = itemView.findViewById(R.id.iv_star_3);
            iv_star_4 = itemView.findViewById(R.id.iv_star_4);
            iv_star_5 = itemView.findViewById(R.id.iv_star_5);
            iv_star_6 = itemView.findViewById(R.id.iv_star_6);
            iv_star_7 = itemView.findViewById(R.id.iv_star_7);
            iv_star_8 = itemView.findViewById(R.id.iv_star_8);
            iv_star_9 = itemView.findViewById(R.id.iv_star_9);
            iv_star_10 = itemView.findViewById(R.id.iv_star_10);

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

        }
    }
}
