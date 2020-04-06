package com.example.myphotogallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.ArrayList;

public class ImageSliderAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<PhotoDetails> allPhotosInFolder;
 //   String imagePath;
    int folderIndex;

    ImageSliderAdapter(Context context, int folderIndex) {
        this.context = context;
        this.folderIndex = folderIndex;
      //  this.imagePath = imagePath;
        this.allPhotosInFolder = MainActivity.allPhotoDetails;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
       // File imgFile = new File("file://" + imagePath);
       // Bitmap myBitmap = BitmapFactory.decodeFile("file://" + imagePath);

      //  imageView.setImageBitmap(myBitmap);

        imageView.setImageURI();

        container.addView(imageView, 0);

//        Glide.with(context).load("file://" + imagePath)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into(imageView);

//        String message = allPhotosInFolder.get(folder_id).getAllPhotosInFolderPaths().get(position);
//
//        Snackbar.make(imageView, message, Snackbar.LENGTH_LONG)
//                .show();
        return imageView;
    }
}
