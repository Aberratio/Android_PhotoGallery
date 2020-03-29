package com.example.myphotogallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class FolderViewAdapter extends ArrayAdapter<PhotoDetails> {

    private Context context;
    private ArrayList<PhotoDetails> allPhotosInFolder;
    private int int_position;


    FolderViewAdapter(Context context, ArrayList<PhotoDetails> allPhotosInFolder, int int_position) {
        super(context, R.layout.photo_gallery, allPhotosInFolder);
        this.allPhotosInFolder = allPhotosInFolder;
        this.context = context;
        this.int_position = int_position;
    }

    @Override
    public int getCount() {
        return allPhotosInFolder.get(int_position).getAllPhotosInFolderPaths().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (allPhotosInFolder.get(int_position).getAllPhotosInFolderPaths().size() > 0) {
            return allPhotosInFolder.get(int_position).getAllPhotosInFolderPaths().size();
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

        FolderView folderView;
        if (inflatedView == null) {
            folderView = new FolderView();
            inflatedView = LayoutInflater.from(getContext()).inflate(R.layout.photo_gallery, parent, false);
            folderView.galleryName = inflatedView.findViewById(R.id.gallery_name);
            folderView.picturesInGalleryAmount = inflatedView.findViewById(R.id.pictures_in_gallery_amount);
            folderView.pictureView = inflatedView.findViewById(R.id.picture_view);

            inflatedView.setTag(folderView);
        } else {
            folderView = (FolderView) inflatedView.getTag();
        }

        folderView.galleryName.setVisibility(View.GONE);
        folderView.picturesInGalleryAmount.setVisibility(View.GONE);

        Glide.with(context).load("file://" + allPhotosInFolder.get(int_position).getAllPhotosInFolderPaths().get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(folderView.pictureView);

        return inflatedView;
    }
}
