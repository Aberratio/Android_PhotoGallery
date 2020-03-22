package com.example.myphotogallery;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

public class PhotosActivity extends AppCompatActivity {
    int position;
    GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.photo_grid_view);
        position = getIntent().getIntExtra("value", 0);
        gridViewAdapter = new GridViewAdapter(this, MainActivity.allPhotos, position);
        gridView.setAdapter(gridViewAdapter);
    }
}