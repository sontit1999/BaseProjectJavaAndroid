package com.example.basejavaandroid.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.basejavaandroid.R;

import java.util.ArrayList;
import java.util.List;

public class CustomChartBMIResult extends View {
    public static final float DEFAUL_BMI = 20;
    public static final int DEFAUL_COLORTRIANGLE = Color.DKGRAY;
    private static final int DEFAULT_TEXT_SIZE = 30;
    Paint mPaint;
    Paint mTextPaint;
    int widthView ;
    int heightView ;
    int lengthRectangle ;
    float scroreBMI;
    int colorTriangle;
    int colorText = Color.BLACK;
    int sizeText;
    public void setScroreBMI(float scroreBMI) {
        this.scroreBMI = scroreBMI;
    }
    List<Rect>  listRect = new ArrayList<>();
    float[] listBeakScore = new float[]{18.5f,25,30};
    int[] listColor = new int[]{Color.parseColor("#138ce3"),Color.parseColor("#1eb37d"),Color.parseColor("#ffbf19"),Color.parseColor("#d83e3e")};
    public CustomChartBMIResult(Context context) {
        super(context);
        // lấy các giá trị người dùng từ file XML
        initPaint();
        scroreBMI = DEFAUL_BMI;
        colorTriangle = DEFAUL_COLORTRIANGLE;

    }

    public CustomChartBMIResult(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroreBMI = DEFAUL_BMI;
        colorTriangle = DEFAUL_COLORTRIANGLE;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartBMIResult);
        this.sizeText = typedArray.getDimensionPixelSize(R.styleable.CustomChartBMIResult_sizeTextScore, DEFAULT_TEXT_SIZE);
        initPaint();
    }

    public CustomChartBMIResult(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        scroreBMI = DEFAUL_BMI;
        colorTriangle = DEFAUL_COLORTRIANGLE;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartBMIResult);
        this.sizeText = typedArray.getDimensionPixelOffset(R.styleable.CustomChartBMIResult_sizeTextScore, DEFAULT_TEXT_SIZE);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mTextPaint = new Paint();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(colorText);
        mTextPaint.setTextSize(sizeText);
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
            heightView = heightSize*3 ;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            heightView = heightSize*3 ;
        } else {
            heightView = 0;
        }
        setMeasuredDimension(widthSize,heightView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        widthView = getWidth();
        heightView = getHeight();
        lengthRectangle = widthView/4;
        float constant =  (float) 2/3;

        for(int i=0;i<4;i++){
           float topRect = (float)heightView/3;
           float bottomRect = (float)heightView/3 + (float)heightView/3;
           listRect.add(new Rect(i*lengthRectangle-1, (int)topRect,(i+1)*lengthRectangle, (int)bottomRect));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<listRect.size();i++){
            drawRectangleWithColor(canvas, listColor[i],listRect.get(i));
        }
        drawTriangleWithColor(canvas,Color.DKGRAY,widthView/4,heightView/6,heightView/6);
    }
    public void drawRectangleWithColor(Canvas canvas,int color, Rect rect){
        // fill
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        canvas.drawRect(rect, mPaint);

    }
    public void drawText(Canvas canvas,int x,int y,String text ){
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();

        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);
        canvas.drawText(str, 0, str.length(), x - width/2, y, mTextPaint);

    }
    public void drawTriangleWithColor(Canvas canvas, int color, int x, int y, int width) {

        int d = widthView/8;
        int XtriAngler = d;
        if(scroreBMI<18.5){
            XtriAngler = d;
            colorTriangle = listColor[0];
        }
        if(scroreBMI>=18.5 && scroreBMI < 25){
            XtriAngler = d*3;
            colorTriangle = listColor[1];
        }
        if(scroreBMI>=25 && scroreBMI <=30){
            XtriAngler = d*5;
            colorTriangle = listColor[2];
        }
        if(scroreBMI>30){
            XtriAngler = d*7;
            colorTriangle = listColor[3];
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        int halfWidth = width/2;

        Path path = new Path();
        path.moveTo(XtriAngler, y + halfWidth); // Top
        path.lineTo(XtriAngler - halfWidth, y - halfWidth); // Bottom left
        path.lineTo(XtriAngler + halfWidth, y - halfWidth); // Bottom right
        path.lineTo(XtriAngler, y + halfWidth); // Back to Top
        path.close();
        mPaint.setColor(colorTriangle);
        canvas.drawPath(path, mPaint);

        for(int i=1;i<4;i++){
            drawText(canvas,i*d*2,y*6,String.valueOf(listBeakScore[i-1]));
        }
    }
}
