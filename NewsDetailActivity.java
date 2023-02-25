package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity  {

    String title, content, desc, imageURL, url;
    private TextView Title, Content, subdesc;
    private ImageView newsIV;
    private Button newsbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("Title");
        content = getIntent().getStringExtra("Content");

        desc = getIntent().getStringExtra("Desc");
        imageURL = getIntent().getStringExtra("Image");
        url = getIntent().getStringExtra("Url");
        Title = findViewById(R.id.TVTitle);
        Content = findViewById(R.id.Content);
        subdesc = findViewById(R.id.Subdesc);
        newsIV = findViewById(R.id.idIVNews);
        newsbtn = findViewById(R.id.btnnews);
        Title.setText(title);
        Content.setText(content);
        subdesc.setText(desc);
        Picasso.get().load(imageURL).into(newsIV);


    }
    public void read(View v){
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


}

