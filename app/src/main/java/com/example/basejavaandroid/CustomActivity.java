package com.example.basejavaandroid;

import androidx.fragment.app.FragmentManager;

import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityCustomBinding;

public class CustomActivity extends BaseActivity<ActivityCustomBinding,CustomViewmodel> {
    PagerAdapter pagerAdapter;
    @Override
    protected void getData() {
         initViewpageer();
    }

    private void initViewpageer() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        pagerAdapter = new PagerAdapter(fragmentManager);
        binding.viewpager.setAdapter(pagerAdapter);
        try {
            binding.indicator.setViewPager(binding.viewpager);
        } catch (PagesLessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    protected Class<CustomViewmodel> getViewModel() {
        return CustomViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom;
    }
}
