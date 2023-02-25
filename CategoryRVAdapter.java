package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {

    ArrayList<CategoryRVModal> categoryRVModals;
    Context context;
    CategoryClickInterface categoryClickInterface;

    public CategoryRVAdapter(ArrayList<CategoryRVModal> categoryRVModals, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRVModals = categoryRVModals;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv_item,parent,false);
        return new CategoryRVAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( CategoryRVAdapter.ViewHolder holder, int position) {
        CategoryRVModal categoryRVModal=categoryRVModals.get(position);
        holder.CategoryTV.setText(categoryRVModal.getCategory());
        Picasso.get().load(categoryRVModal.getCategoryImageUrl()).into(holder.CategoryIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(holder.getAdapterPosition());

            }
        });




    }

    @Override
    public int getItemCount() {
        return categoryRVModals.size();
    }

    public interface CategoryClickInterface{
        @SuppressLint("NotifyDataSetChanged")
        void onCreate(Bundle savedInstanceState);

        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView CategoryTV;
        private ImageView CategoryIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryTV=itemView.findViewById(R.id.idTVCategory);
            CategoryIV=itemView.findViewById(R.id.idIVCategory);
        }
    }
}




