package com.example.basejavaandroid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.GenderAdapter;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityBmiResultBinding;
import com.example.basejavaandroid.model.BmiInfor;

import java.util.ArrayList;
import java.util.List;

public class BmiResultActivity extends BaseActivity<ActivityBmiResultBinding,BmiResultViewmodel> {
    BmiInfor bmiInfor;
    @Override
    protected void getData() {
         iniSpinner();
         Intent intent = getIntent();
         if(intent!=null){
             bmiInfor = (BmiInfor) intent.getSerializableExtra(BmiCaculateActivity.KEY_BMI);
             if(bmiInfor!=null){
                 binding.spinerGender.setSelection(bmiInfor.getPosGendle());
                 binding.edtHeight.setText(bmiInfor.getH() + "");
                 binding.edtWeight.setText(bmiInfor.getW() + "");
             }
         }
    }

    private void iniSpinner() {
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
        binding.btnBackTinhBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.include.ivBack.setOnClickListener(new View.OnClickListener() {
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
    protected Class<BmiResultViewmodel> getViewModel() {
        return BmiResultViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bmi_result;
    }
}