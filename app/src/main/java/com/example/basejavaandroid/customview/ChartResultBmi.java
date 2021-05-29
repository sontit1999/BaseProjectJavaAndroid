package com.example.basejavaandroid.customview;

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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChartResultBmi extends View {
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
    int[] listColor = new int[]{getResources().getColor(R.color.lessweight), getResources().getColor(R.color.normalweight), getResources().getColor(R.color.moreweight), getResources().getColor(R.color.fatweight)};

    public ChartResultBmi(Context context) {
        super(context);
        initView(context, null);
    }

    public ChartResultBmi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ChartResultBmi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        // lấy các giá trị người dùng từ file XML

        scoreBMI = DEFAULT_BMI;
        colorTriangle = DEFAULT_COLOR_TRIANGLE;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(colorText);
        mTextPaint.setTextSize(sizeText);
        Typeface plain = ResourcesCompat.getFont(getContext(), R.font.roboto);
        mTextPaint.setTypeface(plain);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartBMIResult);
            this.sizeText = typedArray.getDimensionPixelOffset(R.styleable.CustomChartBMIResult_sizeTextScore, DEFAULT_TEXT_SIZE);
            typedArray.recycle();
        }
    }

    public float getScoreBMI() {
        return scoreBMI;
    }

    public void setScoreBMI(float scoreBMI) {
        this.scoreBMI = scoreBMI;
        invalidate();
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
        heightView = (int) (widthView * (20 / 344f) * 5);
        setMeasuredDimension(widthSize, heightView);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        widthView = getWidth();
        heightView = getHeight();
        lengthRectangle = widthView / 4;
        listRect.clear();
        for (int i = 0; i < 4; i++) {
            float topRect = (float) ((heightView * 2) / 4);
            float bottomRect = (float) ((heightView * 2) / 4) + (float) heightView / 4;
            listRect.add(new Rect(i * lengthRectangle, (int) topRect, (i + 1) * lengthRectangle, (int) bottomRect));
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
        drawTriangleWithColor(canvas, Color.DKGRAY, (int) (((heightView * 2) / 4) - (((heightView * 0.8) / 10f))), (int) ((heightView * 0.8) / 5f));
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

    public void drawTextCenterPostittionCustom(Canvas canvas, int x, int y, String text, float sizeText, int colorText) {
        float currentSize = mTextPaint.getTextSize();
        int currentColor = mTextPaint.getColor();

        mTextPaint.setTextSize(sizeText);
        mTextPaint.setColor(colorText);

        // custom font
        Typeface currentTypeface = mTextPaint.getTypeface();
        Typeface plain = ResourcesCompat.getFont(getContext(), R.font.roboto);
        mTextPaint.setTypeface(plain);

        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        float width = mTextPaint.measureText(text);
        int height = bounds.height();

        if ((x - width / 2f) < 0) {
            canvas.drawText(text, 0, text.length(), 0, y + height, mTextPaint);
        } else if ((x + width / 2f) > widthView) {
            canvas.drawText(text, widthView - width, y + height, mTextPaint);
        } else {
            canvas.drawText(text, 0, text.length(), x - width / 2f, y + height, mTextPaint);
        }
        mTextPaint.setTextSize(currentSize);
        mTextPaint.setColor(currentColor);
        mTextPaint.setFakeBoldText(false);
        mTextPaint.setTypeface(currentTypeface);
    }

    public void drawBeakPoint(Canvas canvas) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(String.valueOf(listBeakScore[0]), 0, String.valueOf(listBeakScore[0]).length(), bounds);
        int height = bounds.height();
        int y = (heightView / 4) * 3 + height + height / 2;
        int d = widthView / 4;
        for (int i = 1; i < 4; i++) {
            drawTextCenterPostittionX(canvas, i * d, y, String.valueOf(listBeakScore[i - 1]));
        }
        /*int index = 1;
        for (int i = 1; i <= 4; i++) {
            drawTextCenterPostittionXCustomSize(canvas, index * distanmin, (heightView - height / 10), listDescribe[i - 1], sizeText);
            index += 2;
            drawCicleAndTextPosWithColor(canvas, (i - 1) * d, (heightView - height / 10), listColor[i - 1]);
        }*/
    }

    public void drawTriangleWithColor(Canvas canvas, int color, int y, int width) {

        int d = widthView / 8;
        int XtriAngler = d;
        if (scoreBMI < 18.5) {
            colorTriangle = listColor[0];
            XtriAngler = (int) ((scoreBMI * (2 * d)) / 18.5f);
            if (((XtriAngler - width) < 0)) {
                XtriAngler = width;
            }
        }
        if (scoreBMI >= 18.5 && scoreBMI < 25) {
            XtriAngler = (int) (2 * d + ((scoreBMI - 18.5) * (2 * d) / 6.5f));
            colorTriangle = listColor[1];
        }
        if (scoreBMI >= 25 && scoreBMI <= 30) {
            XtriAngler = (int) (4 * d + ((scoreBMI - 25) * (2 * d)) / 5f);
            colorTriangle = listColor[2];
        }
        if (scoreBMI > 30) {
            XtriAngler = (int) (3 * (widthView / 4f) + ((scoreBMI - 30) * (2 * d)) / 5f);
            colorTriangle = listColor[3];
            if (((XtriAngler + width) > widthView)) {
                XtriAngler = widthView - width;
            }
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
        drawTextCenterPostittionCustom(canvas, XtriAngler, 0, ConvertFloatToString(scoreBMI), sizeText * 3f, colorTriangle);
    }

    public String ConvertFloatToString(float number) {
        int frontDot = (int) number;
        float afterDot = number - frontDot;
        if (afterDot == 0) {
            return String.valueOf(frontDot);
        } else {
            DecimalFormat df = new DecimalFormat("#0.#");
            return df.format(number).replace(',', '.');
        }
    }
}
