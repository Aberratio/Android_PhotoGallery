package com.example.myphotogallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PhotoGalleryActivity extends AppCompatActivity {
    int position;
    FolderViewAdapter folderViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.photo_grid_view);
        position = getIntent().getIntExtra("value", 0);
        final Context context = getApplicationContext();

        //if clicked on photo
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    ArrayList<PhotoDetails> x = MainActivity.allPhotoDetails;
                    Intent imageSlider = new Intent(context, ImageSliderActivity.class);

                    ArrayList<PhotoDetails> allPhotosInFolder = MainActivity.allPhotoDetails;
                    String message = allPhotosInFolder.get(position).getAllPhotosInFolderPaths().get(i);
//                    Snackbar.make(adapterView, message, Snackbar.LENGTH_LONG)
//                          .show();

                    imageSlider.putExtra("message", message);
                    imageSlider.putExtra("position", i);

                    //go into slider activity
                    startActivity(imageSlider);
                } catch (Exception e) {
                    String message = e.getMessage();

                    Snackbar.make(adapterView, message, Snackbar.LENGTH_LONG)
                          .show();
                }
            }
        });

        folderViewAdapter = new FolderViewAdapter(this, MainActivity.allPhotoDetails, position);
        gridView.setAdapter(folderViewAdapter);
    }
}