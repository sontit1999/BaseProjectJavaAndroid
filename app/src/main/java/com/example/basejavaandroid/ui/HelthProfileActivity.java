package com.example.basejavaandroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityHelthProfileBinding;

public class HelthProfileActivity extends BaseActivity<ActivityHelthProfileBinding,HelthProfileViewmodel> {

    @Override
    protected void getData() {
          // set tittle toolbar
          binding.include.tvTitle.setText("Sức khỏe");
          Glide
                    .with(this)
                    .load("https://anhdepblog.com/wp-content/uploads/2020/09/anh-gai-xinh-facebook-21.jpg")
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(binding.include.profileImage);
    }

    @Override
    protected void initEvent() {
             binding.include.ivBack.setOnClickListener(v -> finish());
             binding.layoutBMI.setOnClickListener(v -> gotoActivityNoData(BmiActivity.class));
             binding.include.profileImage.setOnClickListener(v -> gotoActivityNoData(MyProfileHelthActivity.class));
    }

    @Override
    protected void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    protected Class<HelthProfileViewmodel> getViewModel() {
        return HelthProfileViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_helth_profile;
    }
}