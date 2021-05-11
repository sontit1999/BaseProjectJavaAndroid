package com.example.basejavaandroid.base;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.customview.TestView;

public class BindingUtils {
    @BindingAdapter({ "setAdapter"})
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }
    @BindingAdapter("onLongClickView")
    public static void setOnLongClickListener(View view,Runnable listener) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.run();
                return false;
            }
        });
    }
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide
                .with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
    @BindingAdapter({"bind:displayFloat"})
    public static void displayFloat(TextView view, float score) {
       view.setText(UtilsFunction.ConvertFloatToString(score));
    }
    @BindingAdapter({"bind:imageAssest"})
    public static void loadImageAssestToview(ImageView view, String imageAssest) {
        String assetPath =   "file:///android_asset/" + imageAssest;
        Glide.with(view.getContext())
                .load(Uri.parse(assetPath))
                .centerCrop()
                .into(view);

    }
    @BindingAdapter({"bind:setBGcolor"})
    public static void setBGcolor(View view, float scroreBMI) {

        int[] listColor = new int[]{Color.parseColor("#138ce3"),Color.parseColor("#1eb37d"),Color.parseColor("#ffbf19"),Color.parseColor("#d83e3e")};
        int color = listColor[0];
        if(scroreBMI<18.5){

            color = listColor[0];
        }
        if(scroreBMI>=18.5 && scroreBMI < 25){

            color = listColor[1];
        }
        if(scroreBMI>=25 && scroreBMI <=30){
            color = listColor[2];
        }
        if(scroreBMI>30){
            color = listColor[3];
        }
        Drawable drawable = view.getContext().getResources().getDrawable(R.drawable.bg_circle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setTint(color);
        }
        view.setBackground(drawable);
    }
}
