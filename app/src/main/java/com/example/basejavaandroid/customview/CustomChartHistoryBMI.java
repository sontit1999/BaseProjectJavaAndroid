package com.example.basejavaandroid.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.basejavaandroid.R;
import com.example.basejavaandroid.model.BmiHistory;

import java.util.ArrayList;
import java.util.List;

public class CustomChartHistoryBMI extends View {
    public static final float DEFAULT_TEXTSIZE = 20;
    public static final float DEFAULT_MAX_SCORE_BMI = 30;
    private static final int DEFAULT_COLOR_TEXT = Color.BLACK;
    List<BmiHistory> listHistory;
    float widhView;
    float heightView;
    float dx, dy;
    float textSize;
    Paint mPaint;
    Paint mPaintDrawRectangeScore;
    Paint mTextPaint;
    int numberColumnRec = 0;
    int yOrigin ;
    String[] listDescribe = new String[]{"Thiếu Cân","Bình Thường","Thừa Cân","Béo Phì"};
    int[] listColor = new int[]{Color.parseColor("#138ce3"),Color.parseColor("#1eb37d"),Color.parseColor("#ffbf19"),Color.parseColor("#d83e3e")};
    private int colorText = Color.BLACK;

    public void setListHistory(List<BmiHistory> listHistory) {
        this.listHistory = listHistory;
        numberColumnRec = this.listHistory.size();
    }

    public CustomChartHistoryBMI(Context context) {
        super(context);
        listHistory = new ArrayList<>();
        numberColumnRec = this.listHistory.size();
    }

    public CustomChartHistoryBMI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        listHistory = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartHistoryBMI);
        this.textSize = typedArray.getDimensionPixelSize(R.styleable.CustomChartHistoryBMI_TextsizeScore, (int) DEFAULT_TEXTSIZE);
        initPaint();
        numberColumnRec = this.listHistory.size();
    }

    private void initPaint() {
        mPaint = new Paint();
        mTextPaint = new Paint();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(colorText);
        mTextPaint.setTextSize(textSize);

        mPaintDrawRectangeScore = new Paint();
        mPaintDrawRectangeScore.setStyle(Paint.Style.FILL);
        mPaintDrawRectangeScore.setColor(DEFAULT_COLOR_TEXT);
    }

    public CustomChartHistoryBMI(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        listHistory = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartHistoryBMI);
        this.textSize = typedArray.getDimensionPixelSize(R.styleable.CustomChartBMIResult_sizeTextScore, (int) DEFAULT_TEXTSIZE);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            widhView = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            widhView = widthSize;
        } else {
            widhView = 0;
        }
        heightView = widhView;

        setMeasuredDimension(widthSize, (int) heightView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        widhView = getWidth();
        heightView = getHeight();
        dx = widhView/14;
        dy = heightView/8;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTextScoreBMIX(canvas);
        drawTextTime(canvas);

    }

    private void drawRectangleScore(Canvas canvas,int bottomX,int bottomY,float scoreBMI) {
        canvas.drawRect(new Rect((int)(bottomX-dx/2f),(int)(bottomY+(scoreBMI*dy)/5f),(int)(bottomX+dx/2f),bottomY),mPaintDrawRectangeScore);
    }

    private void drawTextScoreBMIX(Canvas canvas) {
        float heightMax = 30;
        int numberBreakpoint = 7;
        int index = 0;

        for(int i=0;i<numberBreakpoint;i++){
            if(i==0){
                drawTextCenterPostittion(canvas,(int)dx/2 ,(int)(heightView-(dy*(i+1))),String.valueOf(index),false,false);
                drawLine(canvas, (int) dx,(int)(heightView-(dy*(i+1))), (int) widhView,(int)(heightView-(dy*(i+1))));

            }else if(i==numberBreakpoint-1){
                drawTextCenterPostittion(canvas,(int)dx/2 ,(int)(heightView-(dy*(i+1))),String.valueOf(index),false,true);
                drawLine(canvas, (int) dx,(int)(heightView-(dy*(i+1))), (int) widhView,(int)(heightView-(dy*(i+1))));
            }
            else{
                drawTextCenterPostittion(canvas,(int)dx/2,(int)(heightView-(dy*(i+1))),String.valueOf(index),false,true);
                drawLine(canvas, (int) dx,(int)(heightView-(dy*(i+1))), (int) widhView,(int)(heightView-(dy*(i+1))));
            }
            index+=5;
        }
    }

    private void drawTextTime(Canvas canvas) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds("aaa", 0, 3, bounds);
        int height = bounds.height();
       // int yTime = (int) (heightView-height);
        int yTime = (int) (heightView-height);
        int yOrigin = (int) ( heightView - dy);
        for(int i=1;i<=listHistory.size();i++){
           // drawRectangleWithColor(canvas,Color.RED,(int) ((2*i-1)*dx),yOrigin,listHistory.get(i-1).getScore());
            drawTextCenterPostittion(canvas,(int) ((2*i-1)*dx),yTime,listHistory.get(i-1).getTime().split(" ")[1],false,false);
        }

    }
    public void drawRectangleWithColor(Canvas canvas,int color, int bottomX,int bottomY,float scroreBMI){
        int curentColor = mPaintDrawRectangeScore.getColor();
        int colorRec = listColor[0];
        if(scroreBMI<18.5){
            colorRec = listColor[0];
        }
        if(scroreBMI>=18.5 && scroreBMI < 25){
            colorRec =  listColor[1];
        }
        if(scroreBMI>=25 && scroreBMI <=30){
            colorRec = listColor[2];
        }
        if(scroreBMI>30){
            colorRec = listColor[3];
        }
        float heightRectangle = (scroreBMI*dy)/5f;
        // fill
        mPaintDrawRectangeScore.setStyle(Paint.Style.FILL);
        mPaintDrawRectangeScore.setColor(colorRec);
        Rect rect = new Rect((int)(bottomX),(int)((heightView - heightRectangle)),(int)(bottomX+dx),(int)(bottomY-dy/2f));
        canvas.drawRect(rect, mPaintDrawRectangeScore);
        mPaintDrawRectangeScore.setColor(curentColor);

    }
    public void drawTextCenterPostittionCustomSize(Canvas canvas,Paint mTextPaint,int x,int y,String text ,float sizeText){
        float curentSize = mTextPaint.getTextSize();
        mTextPaint.setTextSize(sizeText);
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();
        int height = bounds.height();
      /*  CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);*/
        canvas.drawText(text,x - width/2, y+height/2, mTextPaint);
        mTextPaint.setTextSize(curentSize);
    }
    public void drawTextCenterPostittion(Canvas canvas,int x,int y,String text,boolean isCenterX,boolean isCenterY){
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();
        int height = bounds.height();
        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);
        if(isCenterX && isCenterY){
            canvas.drawText(str, 0, str.length(), x + width/2, y+height/2, mTextPaint);
        }else if(isCenterX){
            canvas.drawText(str, 0, str.length(), x + width/2, y, mTextPaint);
        }else if(isCenterY){
            canvas.drawText(str, 0, str.length(), x, y+height/2, mTextPaint);
        }else{
            canvas.drawText(str, 0, str.length(), x , y, mTextPaint);
        }
    }
    public void drawText(Canvas canvas,int x,int y,String text ){
        canvas.drawText(text,x,y,mTextPaint);
    }
    public void drawLine(Canvas canvas,int startX,int startY,int endX,int endY){
        canvas.drawLine(startX,startY,endX,endY,mPaint);
    }
}
