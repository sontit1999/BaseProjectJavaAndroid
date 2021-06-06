package com.example.basejavaandroid.ui.mallclub;

import android.graphics.Color;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityHomeFanClubBinding;
import com.example.basejavaandroid.ui.lifestyle.fragment.ViewPagerAdapter;
import com.example.basejavaandroid.ui.mallclub.fragment.ReviewFragment;

public class HomeFanClubActivity extends BaseActivity<ActivityHomeFanClubBinding, FanClubViewmodel> {
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void getData() {

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initEvent() {
        binding.tvMyReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewpagerReview.setCurrentItem(1, false);
                binding.tvMyReview.setTextColor(Color.WHITE);
                binding.tvReview.setTextColor(Color.BLACK);
            }
        });
        binding.tvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewpagerReview.setCurrentItem(0, false);
                binding.tvReview.setTextColor(Color.WHITE);
                binding.tvMyReview.setTextColor(Color.BLACK);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
        setupViewPagerAndTab();
    }

    private void setupViewPagerAndTab() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        ReviewFragment fanClub = new ReviewFragment(false);
        ReviewFragment myReview = new ReviewFragment(true);
        viewPagerAdapter.addFragment(fanClub, "Mall Fanclub Review");
        viewPagerAdapter.addFragment(myReview, "My Review");
        binding.viewpagerReview.setAdapter(viewPagerAdapter);
        binding.viewpagerReview.setEnabled(false);
    }


    @Override
    protected Class<FanClubViewmodel> getViewModel() {
        return FanClubViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_fan_club;
    }

}