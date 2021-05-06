package com.example.basejavaandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basejavaandroid.R;

import java.util.ArrayList;
import java.util.List;

public class GenderAdapter extends ArrayAdapter<String> {
    List<String> list = new ArrayList<>();
    public GenderAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_gender,parent,false);
        TextView tvGender = convertView.findViewById(R.id.tvGender);
        tvGender.setText(list.get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spiner_no_selected,parent,false);
        TextView tvGender = convertView.findViewById(R.id.tvGender);
        tvGender.setText(list.get(position));
        return convertView;
    }
}
