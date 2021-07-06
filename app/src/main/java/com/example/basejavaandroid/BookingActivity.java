package com.example.basejavaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.customview.CongratulationDialog;
import com.example.basejavaandroid.databinding.ActivityBookingBinding;

public class BookingActivity extends BaseActivity<ActivityBookingBinding,BookingActViewmodel> {
    BookingFragment bookingFragment ;
    FragDemo fragDemo ;
    Film film;
    public static final String PARAM_NAME_VOUCHER = "PARAM_NAME_VOUCHER";
    @Override
    protected void getData() {
        Intent intent = getIntent();
        if(intent!=null){
            film = (Film) intent.getSerializableExtra("film");
        }

        bookingFragment = new BookingFragment();
        if(film!=null){
            bookingFragment.setFilm(film);
        }

    }

    @Override
    protected void initEvent() {
         binding.button2.setOnClickListener(v -> showDialogCheckInCongratulation("ahihi"));
    }

    @Override
    protected void setBindingViewmodel() {

         binding.setViewmodel(viewmodel);
    }

    @Override
    protected Class<BookingActViewmodel> getViewModel() {
        return BookingActViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_booking;
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count > 1) {
            super.onBackPressed();
            //additional code
        } else {
            finish();
        }
    }
    public void showDialogCheckInCongratulation(String eventName) {
        CongratulationDialog congratulationDialog = new CongratulationDialog(this);
        congratulationDialog.setCancelable(false);
        congratulationDialog.show();
    }
}