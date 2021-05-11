package com.example.basejavaandroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.GenderAdapter;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityBmiCaculateBinding;
import com.example.basejavaandroid.model.BmiInfor;

import java.util.ArrayList;
import java.util.List;

public class BmiCaculateActivity extends BaseActivity<ActivityBmiCaculateBinding, BmiCaculateViewmodel> {


    public static final String KEY_BMI = "keyBMI";

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
    }

    @Override
    protected void initEvent() {
        binding.include.profileImage.setVisibility(View.INVISIBLE);
        binding.include.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnTinhBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edtWeight.getText().toString().isEmpty() || binding.edtHeight.getText().toString().isEmpty()){
                   showToast("Không được bỏ trống trường nào");
                }else{
                    float w = Float.valueOf(binding.edtWeight.getText().toString());
                    float h = Float.valueOf(binding.edtHeight.getText().toString());
                    int posGendle = binding.spinerGender.getSelectedItemPosition();

                    BmiInfor bmiInfor = new BmiInfor(w,h,posGendle);
                    Intent intent = new Intent(BmiCaculateActivity.this,BmiResultActivity.class);
                    intent.putExtra(KEY_BMI,bmiInfor);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    protected Class<BmiCaculateViewmodel> getViewModel() {
        return BmiCaculateViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bmi_caculate;
    }
}