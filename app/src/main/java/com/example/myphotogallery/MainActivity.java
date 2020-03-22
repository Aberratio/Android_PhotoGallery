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

import java.util.ArrayList;

public class MainActivity extends Activity {
    public static ArrayList<Photo> allPhotos = new ArrayList<>();
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

        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), PhotosActivity.class);
                intent.putExtra("value", i);
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
            findImages();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == WAITING_TIME) {
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    findImages();
                } else {
                    Toast.makeText(MainActivity.this, "No permissions!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @SuppressLint("Recycle")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void findImages() {
        allPhotos.clear();

        int position = 0;
        Uri externalContentPath;
        Cursor cursor;
        int photoIndex, folderIndex;

        String fullImagePath;
        externalContentPath = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] imagesAndFoldersKeeper = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(externalContentPath, imagesAndFoldersKeeper, null, null, orderBy + " DESC");

        assert cursor != null;
        photoIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        folderIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        while (cursor.moveToNext()) {
            fullImagePath = cursor.getString(photoIndex);

            for (int i = 0; i < allPhotos.size(); i++) {
                if (allPhotos.get(i).getFolderPath().equals(cursor.getString(folderIndex))) {
                    isFolder = true;
                    position = i;
                    break;
                } else {
                    isFolder = false;
                }
            }

            if (isFolder) {
                ArrayList<String> allPhotoPathsInFolder = new ArrayList<>(allPhotos.get(position).getFullPhotoPath());
                allPhotoPathsInFolder.add(fullImagePath);
                allPhotos.get(position).setFullPhotoPath(allPhotoPathsInFolder);
            } else {
                ArrayList<String> allPhotoPathsInFolder = new ArrayList<>();
                allPhotoPathsInFolder.add(fullImagePath);
                Photo newPhoto = new Photo();
                newPhoto.setFolderPath(cursor.getString(folderIndex));
                newPhoto.setFullPhotoPath(allPhotoPathsInFolder);

                allPhotos.add(newPhoto);
            }
        }

        photoGalleryAdapter = new PhotoGalleryAdapter(getApplicationContext(), allPhotos);
        photoGridView.setAdapter(photoGalleryAdapter);
    }

}

