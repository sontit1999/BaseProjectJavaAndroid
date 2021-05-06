package com.example.basejavaandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomChartBMIResult extends View {
    Paint mPaint;
    int widthView ;
    int heightView ;
    int lengthRectangle ;
    List<Rect>  listRect = new ArrayList<>();
    int[] listColor = new int[]{Color.parseColor("#138ce3"),Color.parseColor("#1eb37d"),Color.parseColor("#ffbf19"),Color.parseColor("#d83e3e")};
    public CustomChartBMIResult(Context context) {
        super(context);
        initPaint();
    }

    public CustomChartBMIResult(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomChartBMIResult(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
       mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            widthView = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            widthView = widthSize;
        } else {
            widthView = 0;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            heightView = heightSize * 2;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            heightView = heightSize * 2;
        } else {
            heightView = 0;
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        widthView = getWidth();
        heightView = getHeight();
        lengthRectangle = widthView/4;

        for(int i=0;i<4;i++){
           listRect.add(new Rect(i*lengthRectangle-1,0,(i+1)*lengthRectangle,heightView-1));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<listRect.size();i++){
            drawRectangleWithColor(canvas, listColor[i],listRect.get(i));
        }
        drawTriangleWithColor(canvas,Color.DKGRAY,widthView/2,heightView/2,widthView/4);
    }
    public void drawRectangleWithColor(Canvas canvas,int color, Rect rect){
        // fill
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        canvas.drawRect(rect, mPaint);
    }
    public void drawTriangleWithColor(Canvas canvas, int color, int x, int y, int width) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        int halfWidth = heightView / 2;

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, mPaint);
    }
}
