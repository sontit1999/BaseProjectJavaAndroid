package com.example.basejavaandroid.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.basejavaandroid.R;

public class CustomCategoryBMI extends RelativeLayout {
    View viewThin, viewNormal, viewMuch, viewFat;

    public CustomCategoryBMI(Context context) {
        super(context);
        inflate(context, R.layout.category_bmi_view, (ViewGroup) getRootView());
        initView();

    }

    public CustomCategoryBMI(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.category_bmi_view, (ViewGroup) getRootView());
        initView();
    }

    public CustomCategoryBMI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.category_bmi_view, (ViewGroup) getRootView());
        initView();
    }

    private void initView() {
        viewThin = findViewById(R.id.viewthin);
        viewNormal = findViewById(R.id.viewNormal);
        viewMuch = findViewById(R.id.viewMuch);
        viewFat = findViewById(R.id.viewFat);
    }
}
