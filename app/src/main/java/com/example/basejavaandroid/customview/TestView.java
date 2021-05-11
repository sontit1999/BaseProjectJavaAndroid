package com.example.basejavaandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestView extends View {
    Paint mPaint;
    public TestView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
       mPaint = new Paint();
       mPaint.setColor(Color.BLACK);
       mPaint.setAntiAlias(true);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect bounds = new Rect();
        mPaint.getTextBounds("aaa", 0, 3, bounds);
        int height = bounds.height();
        canvas.drawText("Sơn tít",0,height,mPaint);
    }
}
