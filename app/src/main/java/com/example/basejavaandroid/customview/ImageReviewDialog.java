package com.example.basejavaandroid.customview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.ImageSlideAdapter;

import java.util.ArrayList;

public class ImageReviewDialog extends DialogFragment {
    ViewPager viewPager;
    ImageView ibClose;
    TextView tvPositionItem;
    ImageSlideAdapter adapter;
    ArrayList<String> listImage;

    int currentPos;

    public ImageReviewDialog(ArrayList<String> listImage, int currentPos) {
        this.listImage = listImage;
        this.currentPos = currentPos;
    }

    public void setListImage(ArrayList<String> listImage, int currentPos) {
        this.listImage = listImage;
        this.currentPos = currentPos;
    }

    @Override
    public int getTheme() {
        return R.style.DialogThemeFullScreen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_review, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.viewpager);
        ibClose = view.findViewById(R.id.ibClose);
        tvPositionItem = view.findViewById(R.id.tvPositionItem);
        // init list image
        adapter = new ImageSlideAdapter(getContext(), listImage);
        viewPager.setAdapter(adapter);
        if (currentPos >= 0 && listImage != null && listImage.size() > 0) {
            viewPager.setCurrentItem(currentPos);
            tvPositionItem.setText((currentPos + 1) + "/" + listImage.size());
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvPositionItem.setText((position + 1) + "/" + listImage.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ibClose.setOnClickListener(v -> dismiss());
    }

}
