package com.example.basejavaandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ImagePager extends PagerAdapter {
    ArrayList<String> arrImage;
    Context context;

    public ImagePager(ArrayList<String> arrImage, Context context) {
        this.arrImage = arrImage;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(imageView).load(arrImage.get(position)).placeholder(R.drawable.image_placehoder).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((ImageView) object);
    }
}
