package com.example.myphotogallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static android.view.LayoutInflater.*;

public class PhotoGalleryAdapter extends ArrayAdapter<Photo> {

    private Context context;
    private ArrayList<Photo> allPhotosInFolder;

    PhotoGalleryAdapter(Context context, ArrayList<Photo> allPhotosInFolder) {
        super(context, R.layout.photogallery, allPhotosInFolder);
        this.allPhotosInFolder = allPhotosInFolder;
        this.context = context;
    }

    @Override
    public int getCount() {
        return allPhotosInFolder.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (allPhotosInFolder.size() > 0) {
            return allPhotosInFolder.size();
        } else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View inflatedView, ViewGroup parent) {

        GalleryView galleryView;
        if (inflatedView == null) {

            galleryView = new GalleryView();
            inflatedView = from(getContext()).inflate(R.layout.photogallery, parent, false);
            galleryView.galleryName = inflatedView.findViewById(R.id.gallery_name);
            galleryView.picturesInGalleryAmount = inflatedView.findViewById(R.id.pictures_in_gallery_amount);
            galleryView.pictureView = inflatedView.findViewById(R.id.picture_view);

            inflatedView.setTag(galleryView);
        } else {
            galleryView = (GalleryView) inflatedView.getTag();
        }

        galleryView.galleryName.setText(allPhotosInFolder.get(position).getFolderPath());
        galleryView.picturesInGalleryAmount.setText(allPhotosInFolder.get(position).getFullPhotoPath().size()+"");

        Glide.with(context).load("file://" + allPhotosInFolder.get(position).getFullPhotoPath().get(0))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(galleryView.pictureView);

        return inflatedView;
    }
}
