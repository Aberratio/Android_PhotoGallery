package com.example.myphotogallery;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ImageSliderActivity extends AppCompatActivity {

    int position;
    ImageSliderAdapter imageSliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_gallery);

        position = getIntent().getIntExtra("position", 0);

        ViewPager viewPager = findViewById(R.id.view_pager);
        imageSliderAdapter = new ImageSliderAdapter(this, position);
        viewPager.setAdapter(imageSliderAdapter);
    }

}




