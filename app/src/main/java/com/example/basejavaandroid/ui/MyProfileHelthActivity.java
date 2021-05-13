package com.example.basejavaandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.GenderAdapter;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityMyProfileHelthBinding;

import java.util.ArrayList;
import java.util.List;

public class MyProfileHelthActivity extends BaseActivity<ActivityMyProfileHelthBinding,MyProfileHelthViewmodel> {


    @Override
    protected void getData() {
        initSpinerr();
    }

    private void initSpinerr() {
        List<String> gender = new ArrayList<>();
        gender.add("Nam");
        gender.add("Nữ");
        GenderAdapter genderAdapter = new GenderAdapter(this,R.layout.item_spinner_gender,gender);
        // Specify the layout to use when the list of choices appears
        // Apply the adapter to the spinner
        binding.spinerGender.setAdapter(genderAdapter);

        // load default avatar
       // Glide.with(this).load(R.drawable.avatar).into(binding.profileImage);
    }

    @Override
    protected void initEvent() {
       binding.ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
    }

    @Override
    protected void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    protected Class<MyProfileHelthViewmodel> getViewModel() {
        return MyProfileHelthViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_profile_helth;
    }
}