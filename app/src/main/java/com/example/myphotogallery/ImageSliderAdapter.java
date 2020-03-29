package com.example.myphotogallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.io.File;
import java.util.ArrayList;

public class ImageSliderAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<PhotoDetails> allPhotosInFolder;
    int folder_id;

    ImageSliderAdapter(Context context, int folder_id) {
        this.context = context;
        this.folder_id = folder_id;
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
        File imgFile = new File(allPhotosInFolder.get(folder_id).getAllPhotosInFolderPaths().get(position));
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        imageView.setImageBitmap(myBitmap);
        container.addView(imageView, 0);
        return imageView;
    }
}
