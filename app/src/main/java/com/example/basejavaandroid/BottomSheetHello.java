package com.example.basejavaandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.basejavaandroid.base.BaseBottomSheet;
import com.example.basejavaandroid.databinding.ItemBottomSheetDemoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetHello extends BaseBottomSheet<ItemBottomSheetDemoBinding> {
    int counter = 0;
    @Override
    public int getLayoutID() {
        return R.layout.item_bottom_sheet_demo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void ViewCreated() {
        binding.btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                Toast.makeText(getActivity(), "Counter = " + counter, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
