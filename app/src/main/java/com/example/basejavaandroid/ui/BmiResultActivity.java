package com.example.basejavaandroid.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.adapter.GenderAdapter;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.base.UtilsFunction;
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
                 binding.edtHeight.setText(UtilsFunction.ConvertFloatToString(bmiInfor.getH()));
                 binding.edtWeight.setText(UtilsFunction.ConvertFloatToString(bmiInfor.getW()));
                 float scoreBMI = bmiInfor.getScoreBMI();
                 Log.d("sondz", scoreBMI + "");
                 binding.chartBMI.setScoreBMI(scoreBMI);
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
        binding.include.profileImage.setVisibility(View.INVISIBLE);
        binding.btnBackTinhBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float w = Float.valueOf(binding.edtWeight.getText().toString());
                float h = Float.valueOf(binding.edtHeight.getText().toString());
                int posGendle = binding.spinerGender.getSelectedItemPosition();

                BmiInfor bmiInfor = new BmiInfor(w, h, posGendle);
                binding.chartBMI.setScoreBMI(bmiInfor.getScoreBMI()
                );
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
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