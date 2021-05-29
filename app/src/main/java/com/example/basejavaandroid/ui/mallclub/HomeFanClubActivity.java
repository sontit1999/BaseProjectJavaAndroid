package com.example.basejavaandroid.ui.mallclub;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityHomeFanClubBinding;
import com.example.basejavaandroid.ui.mallclub.fragment.ReviewFragment;

public class HomeFanClubActivity extends BaseActivity<ActivityHomeFanClubBinding, FanClubViewmodel> {
    TabReview tabReview = TabReview.REVIEW;
    ReviewFragment reviewFragment;

    @Override
    protected void getData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initEvent() {
        binding.tvReview.setOnClickListener(v -> changeTab(TabReview.REVIEW));
        binding.tvMyReview.setOnClickListener(v -> changeTab(TabReview.MY_REVIEW));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
        addFragmentReview();
    }

    private void addFragmentReview() {
        if (reviewFragment == null) {
            reviewFragment = new ReviewFragment();
        }
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.containerFragment, reviewFragment).commit();
    }

    @Override
    protected Class<FanClubViewmodel> getViewModel() {
        return FanClubViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_fan_club;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void changeTab(TabReview tab) {
        if (tab == TabReview.REVIEW) {
            tabReview = TabReview.REVIEW;
            binding.viewMyReview.setVisibility(View.INVISIBLE);
            binding.viewReview.setVisibility(View.VISIBLE);
            reviewFragment.getData(TabReview.REVIEW);
        } else {
            tabReview = TabReview.MY_REVIEW;
            binding.viewReview.setVisibility(View.INVISIBLE);
            binding.viewMyReview.setVisibility(View.VISIBLE);
            reviewFragment.getData(TabReview.MY_REVIEW);
        }
    }

    public TabReview getTabReview() {
        return tabReview;
    }

    public enum TabReview {
        REVIEW,
        MY_REVIEW
    }
}