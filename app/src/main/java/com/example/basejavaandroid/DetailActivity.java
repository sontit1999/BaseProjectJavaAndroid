package com.example.basejavaandroid;

import android.content.Intent;
import android.view.View;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity<ActivityDetailBinding,DetailViewmodel> {
    Film film;
    BottomSheetHello bottomSheetHello ;
    @Override
    protected void getData() {
        bottomSheetHello = new BottomSheetHello();
        Intent intent = getIntent();
        if(intent!=null){
            film = (Film) intent.getSerializableExtra(MainActivity.FILM_KEY);
        }
        if(film!=null){
            binding.setFilm(film);
        }
    }

    @Override
    protected void initEvent() {
       binding.btnBooking.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                  Intent intentBroadcast = new Intent(MainActivity.ACTION_BROADCAST);
                  intentBroadcast.putExtra(MainActivity.KEY_DATA,film);
                  LocalBroadcastManager.getInstance(DetailActivity.this).sendBroadcast(intentBroadcast);
                  Intent intent = new Intent(DetailActivity.this,BookingActivity.class);
                  intent.putExtra("film",film);
                  startActivity(intent);
           }
       });
    }

    @Override
    protected void setBindingViewmodel() {

    }

    @Override
    protected Class<DetailViewmodel> getViewModel() {
        return DetailViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }
}