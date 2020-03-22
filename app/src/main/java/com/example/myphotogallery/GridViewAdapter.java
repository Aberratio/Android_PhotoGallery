package com.example.myphotogallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<Photo> {

    private Context context;
    private ArrayList<Photo> allPhotosInFolder;
    private int int_position;


    GridViewAdapter(Context context, ArrayList<Photo> allPhotosInFolder, int int_position) {
        super(context, R.layout.photogallery, allPhotosInFolder);
        this.allPhotosInFolder = allPhotosInFolder;
        this.context = context;
        this.int_position = int_position;
    }

    @Override
    public int getCount() {
        return allPhotosInFolder.get(int_position).getFullPhotoPath().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (allPhotosInFolder.get(int_position).getFullPhotoPath().size() > 0) {
            return allPhotosInFolder.get(int_position).getFullPhotoPath().size();
        } else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View inflatedView, ViewGroup parent) {

        GalleryView galleryView;
        if (inflatedView == null) {
            galleryView = new GalleryView();
            inflatedView = LayoutInflater.from(getContext()).inflate(R.layout.photogallery, parent, false);
            galleryView.galleryName = inflatedView.findViewById(R.id.gallery_name);
            galleryView.picturesInGalleryAmount = inflatedView.findViewById(R.id.pictures_in_gallery_amount);
            galleryView.pictureView = inflatedView.findViewById(R.id.picture_view);

            inflatedView.setTag(galleryView);
        } else {
            galleryView = (GalleryView) inflatedView.getTag();
        }

        galleryView.galleryName.setVisibility(View.GONE);
        galleryView.picturesInGalleryAmount.setVisibility(View.GONE);

        Glide.with(context).load("file://" + allPhotosInFolder.get(int_position).getFullPhotoPath().get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(galleryView.pictureView);

        return inflatedView;
    }
}
