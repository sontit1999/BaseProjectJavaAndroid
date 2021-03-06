package com.example.basejavaandroid.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.example.basejavaandroid.R;

import java.util.ArrayList;
import java.util.List;

public class CustomChartBMIResult extends View {
    public static final float DEFAULT_BMI = 20;
    public static final int DEFAULT_COLOR_TRIANGLE = Color.DKGRAY;
    private static final int DEFAULT_TEXT_SIZE = 30;
    Paint mPaint;
    Paint mTextPaint;
    int widthView;
    int heightView;
    int lengthRectangle;
    float scoreBMI;
    int colorTriangle;
    int colorText = Color.BLACK;
    int sizeText;
    float[] listBeakScore = new float[]{18.5f, 25, 30};

    List<Rect> listRect = new ArrayList<>();
    String[] listDescribe = new String[]{"Thiếu Cân", "Bình Thường", "Thừa Cân", "Béo Phì"};
    int[] listColor = new int[]{Color.parseColor("#138ce3"), Color.parseColor("#1eb37d"), Color.parseColor("#ffbf19"), Color.parseColor("#d83e3e")};

    public CustomChartBMIResult(Context context) {
        super(context);
        // lấy các giá trị người dùng từ file XML
        initPaint();
        scoreBMI = DEFAULT_BMI;
        colorTriangle = DEFAULT_COLOR_TRIANGLE;

    }

    public CustomChartBMIResult(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scoreBMI = DEFAULT_BMI;
        colorTriangle = DEFAULT_COLOR_TRIANGLE;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartBMIResult);
        this.sizeText = typedArray.getDimensionPixelSize(R.styleable.CustomChartBMIResult_sizeTextScore, DEFAULT_TEXT_SIZE);
        typedArray.recycle();
        initPaint();
    }

    public CustomChartBMIResult(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        scoreBMI = DEFAULT_BMI;
        colorTriangle = DEFAULT_COLOR_TRIANGLE;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartBMIResult);
        this.sizeText = typedArray.getDimensionPixelOffset(R.styleable.CustomChartBMIResult_sizeTextScore, DEFAULT_TEXT_SIZE);
        typedArray.recycle();
        initPaint();
    }

    public float getScoreBMI() {
        return scoreBMI;
    }

    public void setScoreBMI(float scoreBMI) {
        this.scoreBMI = scoreBMI;
        invalidate();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(colorText);
        mTextPaint.setTextSize(sizeText);
        Typeface plain = ResourcesCompat.getFont(getContext(), R.font.roboto);
        mTextPaint.setTypeface(plain);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            widthView = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            widthView = widthSize;
        } else {
            widthView = 0;
        }
        heightView = (int) (widthView * 0.0569196f * 5);

        setMeasuredDimension(widthSize, heightView);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        widthView = getWidth();
        heightView = getHeight();
        lengthRectangle = widthView / 4;
        listRect.clear();
        for (int i = 0; i < 4; i++) {
            float topRect = (float) ((heightView * 2) / 5);
            float bottomRect = (float) ((heightView * 2) / 5) + (float) heightView / 5;
            listRect.add(new Rect(i * lengthRectangle - 1, (int) topRect, (i + 1) * lengthRectangle, (int) bottomRect));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < listRect.size(); i++) {
//            drawRectangleWithColor(canvas, listColor[i],listRect.get(i));
            if (i == 0) {
                drawRectangleWithColorAndBoderRadius(canvas, listColor[i], listRect.get(i), true);
            } else if (i == listRect.size() - 1) {
                drawRectangleWithColorAndBoderRadius(canvas, listColor[i], listRect.get(i), false);
            } else {
                drawRectangleWithColor(canvas, listColor[i], listRect.get(i));
            }


        }
        drawTriangleWithColor(canvas, Color.DKGRAY, (heightView / 5) + heightView / 8, heightView / 8);
        drawBeakPoint(canvas);
    }

    public void drawRectangleWithColorAndBoderRadius(Canvas canvas, int color, Rect rect, boolean isLeft) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        //draw rectange boder radius
        float boderRadius = (rect.bottom - rect.top) / 2f;
        float[] cornersLeft = new float[]{
                boderRadius, boderRadius,        // Top left radius in px
                0, 0,        // Top right radius in px
                0, 0,          // Bottom right radius in px
                boderRadius, boderRadius           // Bottom left radius in px
        };
        float[] cornersRight = new float[]{
                0, 0,        // Top left radius in px
                boderRadius, boderRadius,        // Top right radius in px
                boderRadius, boderRadius,          // Bottom right radius in px
                0, 0           // Bottom left radius in px
        };
        final Path path = new Path();
        if (isLeft) {
            path.addRoundRect(new RectF(rect.left, rect.top, rect.right, rect.bottom), cornersLeft, Path.Direction.CW);
        } else {
            path.addRoundRect(new RectF(rect.left, rect.top, rect.right, rect.bottom), cornersRight, Path.Direction.CW);
        }
        canvas.drawPath(path, mPaint);
    }

    public void drawRectangleWithColor(Canvas canvas, int color, Rect rect) {
        // fill
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);

        canvas.drawRect(rect, mPaint);

    }

    public void drawTextCenterPostittionX(Canvas canvas, int x, int y, String text) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();

        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);
        canvas.drawText(str, 0, str.length(), x - width / 2f, y, mTextPaint);

    }

    public void drawTextCenterPostittionXCustomSize(Canvas canvas, int x, int y, String text, float sizeText) {
        float curentSize = mTextPaint.getTextSize();
        mTextPaint.setTextSize(sizeText);
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();
        int height = bounds.height();
        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);
        canvas.drawText(str, 0, str.length(), x - width / 2f, y, mTextPaint);
        mTextPaint.setTextSize(curentSize);
    }

    public void drawCicleAndTextPosWithColor(Canvas canvas, int x, int y, int color) {
        int curentColor = mPaint.getColor();
        mPaint.setColor(color);
        int radiusCircle = heightView / 22;
        canvas.drawCircle(x + radiusCircle, y - radiusCircle, radiusCircle, mPaint);
        mPaint.setColor(curentColor);

    }

    public void drawTextCenterPostittionCustom(Canvas canvas, int x, int y, String text, float sizeText, int colorText) {
        float curentSize = mTextPaint.getTextSize();
        int currentColor = mTextPaint.getColor();
        mTextPaint.setTextSize(sizeText);
        mTextPaint.setColor(colorText);
        mTextPaint.setFakeBoldText(true);
        // custom font
        Typeface currentTypeface = mTextPaint.getTypeface();
        Typeface plain = ResourcesCompat.getFont(getContext(), R.font.roboto);
        mTextPaint.setTypeface(plain);

        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();
        int height = bounds.height();

        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);
        canvas.drawText(str, 0, str.length(), x - width / 2f, y + height, mTextPaint);
        mTextPaint.setTextSize(curentSize);
        mTextPaint.setColor(currentColor);
        mTextPaint.setFakeBoldText(false);
        mTextPaint.setTypeface(currentTypeface);
    }

    public void drawBeakPoint(Canvas canvas) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(String.valueOf(listBeakScore[0]), 0, String.valueOf(listBeakScore[0]).length(), bounds);
        int height = bounds.height();

        int y = (heightView / 5) * 3 + height + height / 2;
        int d = widthView / 4;
        int distanmin = widthView / 8;
        for (int i = 1; i < 4; i++) {
            drawTextCenterPostittionX(canvas, i * d, y, String.valueOf(listBeakScore[i - 1]));
        }
        int index = 1;
        for (int i = 1; i <= 4; i++) {
            drawTextCenterPostittionXCustomSize(canvas, index * distanmin, (heightView - height / 10), listDescribe[i - 1], sizeText);
            index += 2;
            drawCicleAndTextPosWithColor(canvas, (i - 1) * d, (heightView - height / 10), listColor[i - 1]);
        }
    }

    public void drawTriangleWithColor(Canvas canvas, int color, int y, int width) {

        int d = widthView / 8;
        int XtriAngler = d;
        if (scoreBMI < 18.5) {
            colorTriangle = listColor[0];
        }
        if (scoreBMI >= 18.5 && scoreBMI < 25) {
            XtriAngler = d * 3;
            colorTriangle = listColor[1];
        }
        if (scoreBMI >= 25 && scoreBMI <= 30) {
            XtriAngler = d * 5;
            colorTriangle = listColor[2];
        }
        if (scoreBMI > 30) {
            XtriAngler = d * 7;
            colorTriangle = listColor[3];
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(XtriAngler, y + halfWidth); // Top
        path.lineTo(XtriAngler - halfWidth, y - halfWidth); // Bottom left
        path.lineTo(XtriAngler + halfWidth, y - halfWidth); // Bottom right
        path.lineTo(XtriAngler, y + halfWidth); // Back to Top
        path.close();
        mPaint.setColor(colorTriangle);
        canvas.drawPath(path, mPaint);
        // draw Score BMI
        @SuppressLint("DefaultLocale") String scoreString = String.format("%.02f", scoreBMI).replace(',', '.');
        drawTextCenterPostittionCustom(canvas, XtriAngler, 0, scoreString, sizeText * 2f, colorTriangle);
    }

}
