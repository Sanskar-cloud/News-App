package com.example.newsapp;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface {

    private RecyclerView NewsRV,CategoryRV;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal>  categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;
    private ProgressBar loadingPB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsRV = findViewById(R.id.idRVNews);
        CategoryRV = findViewById(R.id.idRVCategories);
        articlesArrayList = new ArrayList<>();
        loadingPB=findViewById(R.id.loadingPB);
        categoryRVModalArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList, this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList, this, this);
        NewsRV.setLayoutManager(new LinearLayoutManager(this));
        NewsRV.setAdapter(newsRVAdapter);
        CategoryRV.setAdapter(categoryRVAdapter);
        getCategories();
        getnews("All");
        newsRVAdapter.notifyDataSetChanged();

    }
    @SuppressLint("NotifyDataSetChanged")
    private void getCategories(){
        categoryRVModalArrayList.add(new CategoryRVModal("All", "https://www.pexels.com/photo/people-having-a-concert-1190297/"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology", "https://unsplash.com/photos/Qnlp3FCO2vc?modal=%5B%22AddToCollection%22%2C%7B%22step%22%3A%22AddToCollection%22%2C%22photoId%22%3A%22Qnlp3FCO2vc%22%7D%5D"));
        categoryRVModalArrayList.add(new CategoryRVModal("Science", "https://stock.adobe.com/in/search/images?k=science+background&asset_id=179059335"));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports", "https://unsplash.com/photos/9HI8UJMSdZA"));
        categoryRVModalArrayList.add(new CategoryRVModal("Health", "https://unsplash.com/photos/NTyBbu66_SI"));
        categoryRVModalArrayList.add(new CategoryRVModal("General", "https://unsplash.com/photos/5fNmWej4tAA"));
        categoryRVModalArrayList.add(new CategoryRVModal("Business", "https://unsplash.com/photos/GWe0dlVD9e0"));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment", "https://www.shutterstock.com/image-photo/television-streaming-tv-broadcast-multimedia-wall-1921373024"));


        categoryRVAdapter.notifyDataSetChanged();



    }
    private void getnews(String Category){
        articlesArrayList.clear();

        String CategoryUrl="https://newsapi.org/v2/top-headlines?country=in&category="+Category+"&apiKey=2b45d476e09149dc91ab1f053110ae1e";
        String URL="https://newsapi.org";
        String Allurl="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=2b45d476e09149dc91ab1f053110ae1e";

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFitAPI retroFitAPI=retrofit.create(RetroFitAPI.class);
        Call<NewsModal>call;
        if(Category.equals("All")){
            call=retroFitAPI.getAllNews(Allurl);
        }
        else{
            call=retroFitAPI.getNewsByCategory(CategoryUrl);
        }
        call.enqueue(new Callback<NewsModal>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<NewsModal> call, @NonNull Response<NewsModal> response) {
                NewsModal newsModal=response.body();
                ArrayList<Articles> articles=newsModal.getArticles();
                for(int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getUrl(),articles.get(i).getContent(),articles.get(i).getDescription(),articles.get(i).getUrlToImage()));


                }
                newsRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to Get News", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onCategoryClick(int position) {
        String cAtegory=categoryRVModalArrayList.get(position).getCategory();
        getnews(cAtegory);

    }
}