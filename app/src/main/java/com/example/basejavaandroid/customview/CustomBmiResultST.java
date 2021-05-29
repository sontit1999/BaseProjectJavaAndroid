package com.example.basejavaandroid.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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

public class CustomBmiResultST extends View {
    public static final float DEFAULT_BMI = 20;
    public static final int DEFAULT_COLOR_TRIANGLE = R.color.normalweight;
    private static final int DEFAULT_TEXT_SIZE_SCORE = 30;
    private static final int DEFAULT_TEXT_SIZE_RANGE_SCORE = 20;
    Paint mPaintRect;
    Paint mTextPaintRange;
    Paint mTextPaintScore;
    float widthView;
    float heightView;
    float heightRectangle;
    float widthTriangle;
    int lengthRectangle;
    float dx;
    float scoreBMI;
    int colorTriangle = getResources().getColor(R.color.colorTitleHomeHelth);
    int colorTextRange = getResources().getColor(R.color.colorTitleHomeHelth);
    int colorTextScore = getResources().getColor(R.color.colorTitleHomeHelth);
    int sizeTextScore;
    int sizeTextRangeScore;
    float[] listBeakScore = new float[]{18.5f, 25, 30};
    Rect boundsTextRange = new Rect();
    Rect boundsTextScore = new Rect();
    List<Rect> listRect;
    int[] listColor = new int[]{getResources().getColor(R.color.lessweight), getResources().getColor(R.color.normalweight), getResources().getColor(R.color.moreweight), getResources().getColor(R.color.fatweight)};

    public CustomBmiResultST(Context context) {
        super(context);
        initComponent(context, null);
    }

    public CustomBmiResultST(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initComponent(context, attrs);
    }

    public CustomBmiResultST(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent(context, attrs);
    }

    public float getScoreBMI() {
        return scoreBMI;
    }

    public void setScoreBMI(float scoreBMI) {
        this.scoreBMI = scoreBMI;
        invalidate();
    }

    private void initComponent(Context context, AttributeSet attrs) {
        // get value from attribute
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomBmiResultST);
            this.sizeTextScore = typedArray.getDimensionPixelOffset(R.styleable.CustomBmiResultST_sizeScore, DEFAULT_TEXT_SIZE_SCORE);
            this.sizeTextRangeScore = typedArray.getDimensionPixelOffset(R.styleable.CustomBmiResultST_sizeRangeScore, DEFAULT_TEXT_SIZE_RANGE_SCORE);
            typedArray.recycle();
        }

        // init default
        lengthRectangle = 4;
        scoreBMI = DEFAULT_BMI;
        listRect = new ArrayList<>();

        // init paint
        mPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);

        mTextPaintRange = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaintRange.setColor(colorTextRange);
        mTextPaintRange.setTextSize(sizeTextRangeScore);
        Typeface plain = ResourcesCompat.getFont(getContext(), R.font.roboto);
        mTextPaintRange.setTypeface(plain);

        mTextPaintScore = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaintScore.setColor(colorTextRange);
        mTextPaintScore.setTextSize(sizeTextScore);
        mTextPaintRange.setTypeface(plain);
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
        heightRectangle = (widthView * (5 / 86f));
        widthTriangle = (heightRectangle * 0.6f);

        // get height TextRange
        String rangeScore = "25";
        mTextPaintRange.getTextBounds(rangeScore, 0, rangeScore.length(), boundsTextRange);
        // get height TextScore
        String stringScore = "25.5";
        mTextPaintScore.getTextBounds(rangeScore, 0, rangeScore.length(), boundsTextScore);
        heightView = heightRectangle + 2 * boundsTextRange.height() + 2 * widthTriangle + boundsTextScore.height();
        setMeasuredDimension(widthSize, (int) heightView);
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        dx = widthView / lengthRectangle;
        int yTopRec = (int) (heightView - 2 * boundsTextRange.height() - heightRectangle);
        int yBottomRect = (int) (heightView - 2 * boundsTextRange.height());
        for (int i = 0; i < lengthRectangle; i++) {
            listRect.add(new Rect((int) (i * dx), yTopRec, (int) (i * dx + dx), yBottomRect));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRectangle(canvas);
        drawBeakPoint(canvas);
        drawTextScore(canvas);
    }

    private void drawTextScore(Canvas canvas) {
        drawTriangleWithColor(canvas, (int) (heightView - 2 * boundsTextRange.height() - heightRectangle - widthTriangle / 2f), (int) widthTriangle);
    }

    private void drawBeakPoint(Canvas canvas) {
        for (int i = 1; i < 4; i++) {
            drawTextCenterPosCustomColor(canvas, (int) (dx * i), (int) heightView, colorTextRange, String.valueOf(listBeakScore[i - 1]), true, false, false);
        }
    }

    private void drawRectangle(Canvas canvas) {
        for (int i = 0; i < listRect.size(); i++) {
            if (i == 0) {
                drawRectangleWithColorAndBoderRadius(canvas, listColor[i], listRect.get(i), true, false);
            } else
                drawRectangleWithColorAndBoderRadius(canvas, listColor[i], listRect.get(i), false, i == listRect.size() - 1);
        }
    }

    public void drawTriangleWithColor(Canvas canvas, int y, int width) {
        scoreBMI = Float.parseFloat(new DecimalFormat("#.#").format(scoreBMI).replace(',', '.'));
        int XtriAngler = 0;
        if (scoreBMI < 18.5) {
            colorTriangle = listColor[0];
            XtriAngler = (int) ((scoreBMI * dx) / 18.5f);
            if (((XtriAngler - width) < 0)) {
                XtriAngler = width;
            }
        }
        if (scoreBMI >= 18.5 && scoreBMI < 25) {
            XtriAngler = (int) (dx + ((scoreBMI - 18.5) * (dx) / 6.5f));
            colorTriangle = listColor[1];
        }
        if (scoreBMI >= 25 && scoreBMI < 30) {
            XtriAngler = (int) (dx * 2 + ((scoreBMI - 25) * (dx)) / 5f);
            colorTriangle = listColor[2];
        }
        if (scoreBMI >= 30) {
            XtriAngler = (int) (3 * (widthView / 4f) + ((scoreBMI - 30) * (dx)) / 5f);
            colorTriangle = listColor[3];
            if (((XtriAngler + width) > widthView)) {
                XtriAngler = (int) (widthView - width);
            }
        }

        mPaintRect.setStyle(Paint.Style.FILL);
        mPaintRect.setColor(colorTriangle);
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(XtriAngler, y + halfWidth); // Top
        path.lineTo(XtriAngler - halfWidth, y - halfWidth); // Bottom left
        path.lineTo(XtriAngler + halfWidth, y - halfWidth); // Bottom right
        path.lineTo(XtriAngler, y + halfWidth); // Back to Top
        path.close();
        canvas.drawPath(path, mPaintRect);
        drawScoreBMI(canvas, colorTriangle, XtriAngler, boundsTextScore.height() + 5, scoreBMI, true);
    }

    private void drawScoreBMI(Canvas canvas, int color, int x, int y, float scoreBMI, boolean isBold) {
        String scoreStr = new DecimalFormat("#.#").format(scoreBMI).replace(',', '.');
        Rect bounds = new Rect();
        mTextPaintScore.getTextBounds(scoreStr, 0, scoreStr.length(), bounds);
        mTextPaintScore.setColor(color);
        if (isBold) {
            mTextPaintScore.setFakeBoldText(true);
        }
        float width = mTextPaintScore.measureText(scoreStr);
        CharSequence str = TextUtils.ellipsize(scoreStr,
                (TextPaint) mTextPaintScore, getWidth(),
                TextUtils.TruncateAt.END);
        if (x - width / 2f < 0) {
            canvas.drawText(str, 0, str.length(), 0, y, mTextPaintScore);
        } else if (x + width / 2f > widthView) {
            canvas.drawText(str, 0, str.length(), widthView - width, y, mTextPaintScore);
        } else {
            canvas.drawText(str, 0, str.length(), x - width / 2f, y, mTextPaintScore);
        }
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

    public void drawTextCenterPosCustomColor(Canvas canvas, int x, int y, int colorText, String text, boolean isCenterX, boolean isCenterY, boolean isBold) {
        Rect bounds = new Rect();
        mTextPaintRange.getTextBounds(text, 0, text.length(), bounds);
        int currentColor = mTextPaintRange.getColor();
        mTextPaintRange.setColor(colorText);
        if (isBold) {
            mTextPaintRange.setFakeBoldText(true);
        }
        int width = bounds.width();
        int height = bounds.height();
        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaintRange, getWidth(),
                TextUtils.TruncateAt.END);
        if (isCenterX && isCenterY) {
            canvas.drawText(str, 0, str.length(), x - width / 2f, y + height / 2f, mTextPaintRange);
        } else if (isCenterX) {
            canvas.drawText(str, 0, str.length(), x - width / 2f - 5, y, mTextPaintRange);
        } else if (isCenterY) {
            canvas.drawText(str, 0, str.length(), x, y + height / 2f, mTextPaintRange);
        } else {
            canvas.drawText(str, 0, str.length(), x, y, mTextPaintRange);
        }
        //reset paint
        mTextPaintRange.setColor(currentColor);
        mTextPaintRange.setFakeBoldText(false);
    }

    public void drawRectangleWithColorAndBoderRadius(Canvas canvas, int color, Rect rect, boolean isLeft, boolean isRight) {
        mPaintRect.setStyle(Paint.Style.FILL);
        mPaintRect.setColor(color);
        //draw rectangle border radius
        float borderRadius = (rect.bottom - rect.top) / 2f + 5;
        float[] cornersLeft = new float[]{
                borderRadius, borderRadius,        // Top left radius in px
                0, 0,        // Top right radius in px
                0, 0,          // Bottom right radius in px
                borderRadius, borderRadius           // Bottom left radius in px
        };
        float[] cornersRight = new float[]{
                0, 0,        // Top left radius in px
                borderRadius, borderRadius,        // Top right radius in px
                borderRadius, borderRadius,          // Bottom right radius in px
                0, 0           // Bottom left radius in px
        };
        float[] cornersNo = new float[]{
                0, 0,        // Top left radius in px
                0, 0,       // Top right radius in px
                0, 0,         // Bottom right radius in px
                0, 0           // Bottom left radius in px
        };
        final Path path = new Path();
        if (isLeft) {
            path.addRoundRect(new RectF(rect.left, rect.top, rect.right, rect.bottom), cornersLeft, Path.Direction.CW);
        } else if (isRight) {
            path.addRoundRect(new RectF(rect.left, rect.top, rect.right, rect.bottom), cornersRight, Path.Direction.CW);
        } else {
            path.addRoundRect(new RectF(rect.left, rect.top, rect.right, rect.bottom), cornersNo, Path.Direction.CW);
        }
        canvas.drawPath(path, mPaintRect);
    }
}
