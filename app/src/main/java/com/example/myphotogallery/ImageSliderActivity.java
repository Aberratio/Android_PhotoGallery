package com.example.myphotogallery;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ImageSliderActivity extends AppCompatActivity {

    String imagePath;
    ImageSliderAdapter imageSliderAdapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_slider);

        imagePath = getIntent().getDataString();
        position = getIntent().getIntExtra("position", 0);

        ViewPager viewPager = findViewById(R.id.view_pager);
        imageSliderAdapter = new ImageSliderAdapter(this, imagePath);

        viewPager.setAdapter(imageSliderAdapter);
        viewPager.setCurrentItem(position);
    }

}




