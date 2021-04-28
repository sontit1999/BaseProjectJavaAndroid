package com.example.basejavaandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
        Log.d("son","onCreate");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("son","onActack");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("son","onCreatView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("son","onViewCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("son","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("son","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("son","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("son","onStop");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("son","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("son","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("son","onDetack");
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d("son","onDissmiss");
    }
}
