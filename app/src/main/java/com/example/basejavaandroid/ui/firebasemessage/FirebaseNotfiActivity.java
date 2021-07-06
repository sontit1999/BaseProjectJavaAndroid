package com.example.basejavaandroid.ui.firebasemessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityFirebaseNotfiBinding;

import java.text.DecimalFormat;

public class FirebaseNotfiActivity extends BaseActivity<ActivityFirebaseNotfiBinding,FirebaseViewModel> {

    @Override
    protected void getData() {

    }

    @Override
    protected void initEvent() {
        binding.btnTinh.setOnClickListener(v -> {
            int number  = Integer.parseInt(binding.editText.getText().toString());
            binding.tvKQ.setText(getRoughNumberLike(number));
        });
    }

    @Override
    protected void setBindingViewmodel() {

    }

    @Override
    protected Class<FirebaseViewModel> getViewModel() {
        return FirebaseViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_firebase_notfi;
    }
    public static String getRoughNumberLike(long value) {
        if (value <= 999) {
            return String.valueOf(value);
        }
        final String[] units = new String[]{"", "K", "M", "B", "P"};
        int digitGroups = (int) (Math.log10(value) / Math.log10(1000));
        return new DecimalFormat("#,##0.#").format(value / Math.pow(1000, digitGroups)).replace(',','.') + "" + units[digitGroups];

    }
}