package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder> {

    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
        return new NewsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles articles=articlesArrayList.get(position);
        holder.titleTV.setText(articles.getTitle());
        holder.subTitleTV.setText(articles.getDescription());
        Picasso.get().load(articles.getUrlToImage()).into(holder.NewsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,NewsDetailActivity.class);
                i.putExtra("Title",articles.getTitle());
                i.putExtra("Content",articles.getContent());
                i.putExtra("Desc",articles.getDescription());
                i.putExtra("Image",articles.getUrlToImage());
                i.putExtra("Url",articles.getUrl());
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTV,subTitleTV;
        private ImageView NewsIV;


        public ViewHolder(@NonNull View itemView) {






            super(itemView);
            titleTV=itemView.findViewById(R.id.idTVHeading);
            subTitleTV=itemView.findViewById(R.id.idTVsubHeading);
            NewsIV=itemView.findViewById(R.id.idIVNews);

        }
    }
}