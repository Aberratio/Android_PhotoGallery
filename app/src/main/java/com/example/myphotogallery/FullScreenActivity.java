package com.example.myphotogallery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;


public class FullScreenActivity extends AppCompatActivity {
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen);

        ImageView fullScreenImageView = findViewById(R.id.full_screen_image_view);
        Intent intent = getIntent();
        String pos = intent.getStringExtra("position");
        Glide.with(this).load(pos).into(fullScreenImageView);

//        setContentView;
//
//        ImageView showPhotoImageView = findViewById(R.id.full_screen);
//        GridView gridView = findViewById(R.id.photo_grid_view);

//        Intent intent = getIntent();
//
//        int position = getIntent().getIntExtra("position", 0);
//
//        if(intent != null) {
//            if(showPhotoImageView != null) {
//                Glide.with(this).load("file://"  + MainActivity.allPhotos.get(position).getFullPhotoPath().get(0))
//                        .skipMemoryCache(false)
//                        .into(showPhotoImageView);
//            }
//        }

//        String message = "You clicked on uri " + position;
//
//                Snackbar.make(showPhotoImageView, message, Snackbar.LENGTH_LONG)
//                        .show();
/*
        gridViewAdapter = new GridViewAdapter(this, MainActivity.allPhotos, position);
        gridView.setAdapter(gridViewAdapter);*/
    }
}
