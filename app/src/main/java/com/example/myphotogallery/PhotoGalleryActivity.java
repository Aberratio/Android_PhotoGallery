package com.example.myphotogallery;

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

        //if clicked on folder
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<PhotoDetails> x = MainActivity.allPhotoDetails;

                //ArrayList<String> y = x.get(position).getFullPhotoPath();
//                String message = "You clicked on uri " + y.get(i);
//
//                Snackbar.make(adapterView, message, Snackbar.LENGTH_LONG)
//                        .show();

                Intent fullScreen = new Intent(getApplicationContext(), FullScreenActivity.class);

                String message = "file:/"  + MainActivity.allPhotoDetails.get(position).getAllPhotosInFolderPaths().get(i);
                fullScreen.putExtra("position", message);
                Snackbar.make(adapterView, message, Snackbar.LENGTH_LONG)
                       .show();

               startActivity(fullScreen);
            }
        });

        folderViewAdapter = new FolderViewAdapter(this, MainActivity.allPhotoDetails, position);
        gridView.setAdapter(folderViewAdapter);
    }
}