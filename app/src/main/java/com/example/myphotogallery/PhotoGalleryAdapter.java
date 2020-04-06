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

public class PhotoGalleryAdapter extends ArrayAdapter<PhotoDetails> {

    private Context context;
    private ArrayList<PhotoDetails> allFolders;

    PhotoGalleryAdapter(Context context, ArrayList<PhotoDetails> allPhotosInFolder) {
        super(context, R.layout.photo_gallery, allPhotosInFolder);
        this.allFolders = allPhotosInFolder;
        this.context = context;
    }

    @Override
    public int getCount() {
        return allFolders.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (allFolders.size() > 0) {
            return allFolders.size();
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

        FolderView folderView;
        if (inflatedView == null) {

            folderView = new FolderView();
            inflatedView = from(getContext()).inflate(R.layout.photo_gallery, parent, false);
            folderView.galleryName = inflatedView.findViewById(R.id.gallery_name);
            folderView.picturesInGalleryAmount = inflatedView.findViewById(R.id.pictures_in_gallery_amount);
            folderView.pictureView = inflatedView.findViewById(R.id.picture_view);

            inflatedView.setTag(folderView);
        } else {
            folderView = (FolderView) inflatedView.getTag();
        }

        folderView.galleryName.setText(allFolders.get(position).getFolderName());
        folderView.picturesInGalleryAmount.setText(allFolders.get(position).getAllPhotosInFolderPaths().size()+"");

        Glide.with(context).load("file://" + allFolders.get(position).getAllPhotosInFolderPaths().get(0))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(folderView.pictureView);

        return inflatedView;
    }
}
