package com.example.basejavaandroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityBmiBinding;

public class BmiActivity extends BaseActivity<ActivityBmiBinding,BMiViewmodel> {


    @Override
    protected void getData() {
          binding.include.tvTitle.setText("BMI");
    }

    @Override
    protected void initEvent() {
        binding.include.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.include.profileImage.setVisibility(View.INVISIBLE);
        binding.btnCalucateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gotoActivityNoData(BmiCaculateActivity.class);
            }
        });
        binding.btnHistoryBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoActivityNoData(BmiHistoryActivity.class);
            }
        });
    }

    @Override
    protected void setBindingViewmodel() {
          binding.setViewmodel(viewmodel);
    }

    @Override
    protected Class<BMiViewmodel> getViewModel() {
        return BMiViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bmi;
    }
}