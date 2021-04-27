package com.example.basejavaandroid;

import android.view.View;
import android.widget.Toast;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.BaseDialogFragment;
import com.example.basejavaandroid.databinding.ItemBottomSheetDemoBinding;

public class DialogHello extends BaseDialogFragment<ItemBottomSheetDemoBinding> {
    @Override
    public int getLayoutID() {
        return R.layout.item_bottom_sheet_demo;
    }

    @Override
    public void ViewCreated() {
        binding.btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Dialog helloo sơn tít nhaa!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
