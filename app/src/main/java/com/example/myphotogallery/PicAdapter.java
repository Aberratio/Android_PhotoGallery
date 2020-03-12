package com.example.myphotogallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.lang.Object;

public class PicAdapter extends PagerAdapter {
    int defaultItemBackground;
    private Context galleryContext;
    private Bitmap[] imageBitmaps;
    Bitmap placeholder;

    public PicAdapter(Context c) {
        galleryContext = c;
        imageBitmaps  = new Bitmap[10];
        placeholder = BitmapFactory.decodeResource(galleryContext.getResources(), R.drawable.ic_launcher_background);

        for(int i=0; i<imageBitmaps.length; i++)
            imageBitmaps[i]=placeholder;

        TypedArray styleAttrs = galleryContext.obtainStyledAttributes(R.styleable.PicGallery);
        defaultItemBackground = styleAttrs.getResourceId(
                R.styleable.PicGallery_android_galleryItemBackground, 0);

        styleAttrs.recycle();
    }

    public int getCount() {
        return imageBitmaps.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewPager pager = (ViewPager) container;
        View view = getView(position, pager);

        pager.addView(view);

        return view;
    }
    //return item at specified position
    public Object getItem(int position) {
        return position;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }
    //return item ID at specified position
    public long getItemId(int position) {
        return position;
    }

    //get view specifies layout and display options for each thumbnail in the gallery
    public View getView(int position, ViewPager pager) {
        //create the view
        ImageView imageView = new ImageView(galleryContext);
        //specify the bitmap at this position in the array
        imageView.setImageBitmap(imageBitmaps[position]);
        //set layout options
        imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
        //scale type within view area
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //set default gallery item background
        imageView.setBackgroundResource(defaultItemBackground);
        //return the view
        return imageView;
    }
}