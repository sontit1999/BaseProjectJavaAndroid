package com.example.basejavaandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityBmiHistoryBinding;
import com.example.basejavaandroid.model.BmiHistory;

import java.util.List;

public class BmiHistoryActivity extends BaseActivity<ActivityBmiHistoryBinding,BmiHistoryViewmodel> {


    @Override
    protected void getData() {

        binding.include.tvTitle.setText("Lịch sử BMI");
        viewmodel.getArrHistory().observe(this, new Observer<List<BmiHistory>>() {
            @Override
            public void onChanged(List<BmiHistory> bmiHistories) {
                viewmodel.bmiHistoryAdapter.setList(bmiHistories);
                binding.chartHistory.setListHistory(bmiHistories);
            }
        });
    }

    @Override
    protected void initEvent() {
        binding.include.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void setBindingViewmodel() {
        binding.setViewmdoel(viewmodel);
        initRvHistory();
    }

    private void initRvHistory() {
       binding.rvHistory.setHasFixedSize(true);
    }

    @Override
    protected Class<BmiHistoryViewmodel> getViewModel() {
        return BmiHistoryViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bmi_history;
    }
}