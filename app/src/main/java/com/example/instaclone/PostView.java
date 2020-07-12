package com.example.instaclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PostView extends AppCompatActivity {

    TextView tvUsername;
    ImageView ivImage;
    TextView tvDescription;
    TextView tvCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        this.getSupportActionBar().hide();

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreated = findViewById(R.id.tvCreated);

        String author = getIntent().getStringExtra("author");
        String caption = getIntent().getStringExtra("caption");
        String position = getIntent().getStringExtra("position");
        String createdAt = getIntent().getStringExtra("createdAt");
        String postUrl = getIntent().getStringExtra("posturl");


        tvUsername.setText(author);
        tvDescription.setText(caption);
        tvCreated.setText(createdAt);

        Glide.with(getApplicationContext()).load(postUrl).into(ivImage);
    }
}