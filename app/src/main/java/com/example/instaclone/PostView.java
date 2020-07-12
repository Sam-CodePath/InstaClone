package com.example.instaclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

public class PostView extends AppCompatActivity {

    TextView tvUsername;
    ImageView ivImage;
    TextView tvDescription;
    TextView tvCreated;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        this.getSupportActionBar().hide();

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreated = findViewById(R.id.tvCreated);
        profileImage = findViewById(R.id.profile_image);

        String author = getIntent().getStringExtra("author");
        String caption = getIntent().getStringExtra("caption");
        String position = getIntent().getStringExtra("position");
        String createdAt = getIntent().getStringExtra("createdAt");
        String postUrl = getIntent().getStringExtra("posturl");
        String profileUrl = getIntent().getStringExtra("profileUrl");


        tvUsername.setText(author);
        tvDescription.setText(caption);
        String createdText = "Posted on: " + createdAt;
        tvCreated.setText(createdText);

        Glide.with(getApplicationContext()).load(postUrl).into(ivImage);

        Glide.with(getApplicationContext()).load(profileUrl).into(profileImage);


    }
}