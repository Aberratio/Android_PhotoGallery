package com.example.myphotogallery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends Activity {

    //variable for selection intent
    private final int PICKER = 1;
    //variable to store the currently selected image
    private int currentPic = 0;
    //gallery object
    private ViewPager picGallery;
    //image view for larger display
    private ImageView picView;
    //adapter for gallery view
    private PicAdapter imgAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picGallery = findViewById(R.id.gallery);

        picView = findViewById(R.id.picture);

        imgAdapt = new PicAdapter(this);

        picGallery.setAdapter(imgAdapt);
    }
}

