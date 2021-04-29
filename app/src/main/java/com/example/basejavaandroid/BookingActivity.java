package com.example.basejavaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityBookingBinding;

public class BookingActivity extends BaseActivity<ActivityBookingBinding,BookingActViewmodel> {
    BookingFragment bookingFragment ;
    Film film;
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
          loadFragment(bookingFragment,R.id.containerFrame);
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
}