package com.example.basejavaandroid.ui;

import android.view.View;

import androidx.lifecycle.Observer;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityBmiHistoryBinding;
import com.example.basejavaandroid.model.BmiHistoryRes;

import java.util.List;

public class BmiHistoryActivity extends BaseActivity<ActivityBmiHistoryBinding,BmiHistoryViewmodel> {


    @Override
    protected void getData() {

        binding.include.tvTitle.setText("Lịch sử BMI");
        viewmodel.getArrHistory().observe(this, new Observer<List<BmiHistoryRes>>() {
            @Override
            public void onChanged(List<BmiHistoryRes> bmiHistoryRes) {
                binding.chartHistory.setListHistory(bmiHistoryRes);
                viewmodel.bmiHistoryAdapter.setList(bmiHistoryRes);
            }
        });
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