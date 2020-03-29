package com.example.myphotogallery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public static ArrayList<PhotoDetails> allPhotoDetails = new ArrayList<>();
    public ArrayList<Integer> folderIndexKeeper = new ArrayList<>();
    boolean isFolder;
    GridView photoGridView;
    PhotoGalleryAdapter photoGalleryAdapter;

    private static final int WAITING_TIME = 600;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoGridView = findViewById(R.id.photo_grid_view);

        //if clicked on folder
        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String message = "You clicked on folder " + position;
                Snackbar.make(adapterView, message, Snackbar.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(getApplicationContext(), PhotoGalleryActivity.class);
                intent.putExtra("value", position);

                //go into folder with images
                startActivity(intent);
            }
        });

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) || (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                WAITING_TIME);
                    }
        } else {
            loadImagesIntoGallery();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == WAITING_TIME) {
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    loadImagesIntoGallery();
                } else {
                    Toast.makeText(MainActivity.this, "No permissions!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @SuppressLint("Recycle")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void loadImagesIntoGallery() {
        allPhotoDetails.clear();

        Uri externalContentPath;
        Cursor cursor;
        int photoIndex;
        int folderIndex;
        String fullImagePath;

        externalContentPath = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] imagesAndFoldersKeeper = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(externalContentPath, imagesAndFoldersKeeper, null, null, orderBy + " DESC");

        assert cursor != null;
        photoIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        folderIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        int position = 0;
        while (cursor.moveToNext()) {
            fullImagePath = cursor.getString(photoIndex);

            for (int i = 0; i < allPhotoDetails.size(); i++) {
                if (allPhotoDetails.get(i).getFolderName().equals(cursor.getString(folderIndex))) {
                    isFolder = true;
                    position = i;
                    break;
                } else {
                    isFolder = false;
                }
            }

            if (isFolder) {
                folderIndexKeeper.add(position);
                ArrayList<String> allPhotoPathsInFolder = new ArrayList<>(allPhotoDetails.get(position).getAllPhotosInFolderPaths());
                allPhotoPathsInFolder.add(fullImagePath);
                allPhotoDetails.get(position).setAllPhotosInFolderPaths(allPhotoPathsInFolder);
            } else {
                ArrayList<String> allPhotoPathsInFolder = new ArrayList<>();
                allPhotoPathsInFolder.add(fullImagePath);
                PhotoDetails newPhotoDetails = new PhotoDetails();
                newPhotoDetails.setFolderName(cursor.getString(folderIndex));
                newPhotoDetails.setAllPhotosInFolderPaths(allPhotoPathsInFolder);

                allPhotoDetails.add(newPhotoDetails);
            }
        }

        photoGalleryAdapter = new PhotoGalleryAdapter(getApplicationContext(), allPhotoDetails);
        photoGridView.setAdapter(photoGalleryAdapter);
    }
}

