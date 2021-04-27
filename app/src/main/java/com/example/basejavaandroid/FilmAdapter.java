package com.example.basejavaandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.base.BaseAdapter;
import com.example.basejavaandroid.base.BaseCBAdapter;
import com.example.basejavaandroid.databinding.ItemFilmBinding;


import java.util.List;

public class FilmAdapter extends BaseAdapter<Film, ItemFilmBinding> {
    FilmCallback callback;

    public void setCallback(FilmCallback callback) {
        this.callback = callback;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_film;
    }

    @Override
    protected int getIdVariable() {
        return BR.film;
    }

    @Override
    protected int getIdVariableOnclick() {
        return BR.callback;
    }

    @Override
    protected BaseCBAdapter getOnclick() {
        return callback;
    }
}
