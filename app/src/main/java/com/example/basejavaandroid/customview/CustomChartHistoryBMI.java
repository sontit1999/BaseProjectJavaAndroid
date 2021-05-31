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
import com.example.basejavaandroid.model.BmiHistoryRes;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomChartHistoryBMI extends View {
    public static final float DEFAULT_TEXT_SIZE = 20;
    private static final int DEFAULT_COLOR_TEXT = Color.BLACK;
    private final int colorText = Color.BLACK;
    List<BmiHistoryRes> listHistory;
    float widhView;
    float heightView;
    float dx, dy;
    float textSize;
    float maxScore = 0;
    Paint mPaintColumn;
    int yOrigin, heightTextDate;
    Paint mPaintDrawRectangleScore;
    Paint mTextPaint;
    int numberColumnRec = 0;
    int[] listColor = new int[]{getResources().getColor(R.color.lessweight), getResources().getColor(R.color.normalweight), getResources().getColor(R.color.moreweight), getResources().getColor(R.color.fatweight)};
    int colorLine = getResources().getColor(R.color.color_line);

    public CustomChartHistoryBMI(Context context) {
        super(context);
        listHistory = new ArrayList<>();
        numberColumnRec = 0;
    }

    public CustomChartHistoryBMI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        listHistory = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartHistoryBMI);
        this.textSize = typedArray.getDimensionPixelSize(R.styleable.CustomChartHistoryBMI_TextsizeScore, (int) DEFAULT_TEXT_SIZE);
        initPaint();
        numberColumnRec = this.listHistory.size();
        typedArray.recycle();
    }

    public CustomChartHistoryBMI(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        listHistory = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomChartHistoryBMI);
        this.textSize = typedArray.getDimensionPixelSize(R.styleable.CustomChartBMIResult_sizeTextScore, (int) DEFAULT_TEXT_SIZE);
        initPaint();
        typedArray.recycle();
    }

    public void setListHistory(List<BmiHistoryRes> listHistory) {
        if (listHistory != null) {
            if (listHistory.size() <= 7) {
                this.listHistory = listHistory;
            } else {
                this.listHistory = listHistory.subList(0, 7);
            }
            findMaxScore();
        }
        // Collections.reverse(this.listHistory);
        numberColumnRec = this.listHistory.size();
        invalidate();
    }

    private void findMaxScore() {
        for (BmiHistoryRes i : listHistory) {
            if (i.getScore() >= maxScore) {
                maxScore = (float) i.getScore();
            }
        }
    }

    private void initPaint() {
        Typeface plain = ResourcesCompat.getFont(getContext(), R.font.roboto);

        mPaintColumn = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(colorText);
        mTextPaint.setTextSize(textSize);

        mPaintDrawRectangleScore = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintDrawRectangleScore.setStyle(Paint.Style.FILL);
        mPaintDrawRectangleScore.setColor(DEFAULT_COLOR_TEXT);

        mTextPaint.setTypeface(plain);
        mPaintColumn.setTypeface(plain);
        mPaintDrawRectangleScore.setTypeface(plain);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            widhView = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            widhView = widthSize;
        } else {
            widhView = 0;
        }
        // tính height text date
        String date = "25/05";
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(date, 0, date.length(), bounds);
        heightTextDate = bounds.height();
        heightView = widhView + 2 * heightTextDate;
        setMeasuredDimension(widthSize, (int) heightView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        widhView = getWidth();
        heightView = getHeight();
        dx = widhView / 14;
        dy = (widhView) / 7;
        yOrigin = (int) (heightView - 2 * heightTextDate);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTextScoreBMIX(canvas);
        drawTextTime(canvas);
        //  drawBeakPoint(canvas);
    }

    private void drawTextScoreBMIX(Canvas canvas) {
        int numberBreakpoint = 7;

        float scoreOfRange = maxScore / numberBreakpoint;
        float indexFloat = 0f;

        int index = 0;

        for (int i = 0; i <= numberBreakpoint; i++) {
            Rect bounds = new Rect();
            mTextPaint.getTextBounds(String.valueOf(index), 0, String.valueOf(index).length(), bounds);
            int width = bounds.width();
            int height = bounds.height();
            float yBreakPoint = (yOrigin - (index * (yOrigin / maxScore)));
            if (i == 0) {
                drawTextCenterPostition(canvas, (int) (dx / 2 - width), (int) (yOrigin - i * dy), String.valueOf(index), true, false);
                drawLine(canvas, (int) dx, (int) yBreakPoint, (int) widhView, (int) yBreakPoint);

            } else if (i == numberBreakpoint) {
                drawTextCenterPostition(canvas, (int) (dx / 2 - width), height, String.valueOf(index), true, true);
                drawLine(canvas, (int) dx, 0, (int) widhView, 0);
            } else {
                drawTextCenterPostition(canvas, (int) (dx / 2 - width), (int) yBreakPoint, String.valueOf(index), true, true);
                drawLine(canvas, (int) dx, (int) yBreakPoint, (int) widhView, (int) yBreakPoint);
            }
            indexFloat += scoreOfRange;
            index = roundFloatToInt(indexFloat);
        }
    }

    private void drawTextTime(Canvas canvas) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(String.valueOf(R.string.bmi), 0, 3, bounds);
        int height = bounds.height();
        int yTime = yOrigin + 2 * heightTextDate;
        for (int i = 1; i <= listHistory.size(); i++) {
            mTextPaint.getTextBounds(String.valueOf(listHistory.get(i - 1).getScore()), 0, String.valueOf(listHistory.get(i - 1).getScore()).length(), bounds);
            drawRectangleWithColor(canvas, (int) ((2 * i - 1) * dx), (float) listHistory.get(i - 1).getScore());
            drawTextTime(canvas, (int) ((2 * i - 1) * dx), yTime, ConvertTimeToString(listHistory.get(i - 1).getUpdateAt()), false, false);
            drawScoreBMI(canvas, mTextPaint, (int) ((2 * i - 1) * dx + dx / 2f), yOrigin, ConvertFloatToString((float) listHistory.get(i - 1).getScore()), textSize * 1.2f, false, Color.WHITE);
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

    public int roundFloatToInt(float num) {
        int font = (int) num;
        float affter = num - font;
        if (affter < 0.5) {
            return font;
        } else {
            return font + 1;
        }
    }



    public void drawRectangleWithColor(Canvas canvas, int left, float scoreBMI) {
        int currentColor = mPaintDrawRectangleScore.getColor();
        int colorRec = Color.BLACK;
        if (scoreBMI < 18.5f) {
            colorRec = listColor[0];
        }
        if (scoreBMI >= 18.5f && scoreBMI <= 24.9f) {
            colorRec = listColor[1];
        }
        if (scoreBMI >= 25f && scoreBMI <= 29.9) {
            colorRec = listColor[2];
        }
        if (scoreBMI >= 30) {
            colorRec = listColor[3];
        }
        float heightRectangle = (scoreBMI * yOrigin) / maxScore;
        // fill
        mPaintDrawRectangleScore.setStyle(Paint.Style.FILL);
        mPaintDrawRectangleScore.setColor(colorRec);
        Rect rect = new Rect(left, (int) ((yOrigin - heightRectangle)), (int) (left + dx), yOrigin);
        drawRectangleWithColorAndBoderRadius(canvas, colorRec, rect);
        // canvas.drawRect(rect, mPaintDrawRectangleScore);
        mPaintDrawRectangleScore.setColor(currentColor);
    }

    public void drawScoreBMI(Canvas canvas, Paint mTextPaint, int x, int y, String text, float sizeText, boolean isBold, int colorText) {
        float currentSize = mTextPaint.getTextSize();
        mTextPaint.setTextSize(sizeText);
        int currentColor = mTextPaint.getColor();
        mTextPaint.setColor(colorText);
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        if (isBold) {
            mTextPaint.setFakeBoldText(true);
        }
        int width = bounds.width();
        int height = bounds.height();
        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);

        canvas.drawText(str, 0, str.length(), x - width / 2, y - height, mTextPaint);
        mTextPaint.setTextSize(currentSize);
        mTextPaint.setColor(currentColor);

    }

    public void drawTextCenterPostition(Canvas canvas, int x, int y, String text, boolean isCenterX, boolean isCenterY) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();
        int height = bounds.height();
        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);
        if (isCenterX && isCenterY) {
            canvas.drawText(str, 0, str.length(), x + width / 2f, y + height / 2f, mTextPaint);
        } else if (isCenterX) {
            canvas.drawText(str, 0, str.length(), x + width / 2f, y, mTextPaint);
        } else if (isCenterY) {
            canvas.drawText(str, 0, str.length(), x, y + height / 2f, mTextPaint);
        } else {
            canvas.drawText(str, 0, str.length(), x, y, mTextPaint);
        }
    }

    public void drawTextTime(Canvas canvas, int x, int y, String text, boolean isCenterX, boolean isCenterY) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.width();
       /* CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);*/

        if (x + width > widhView) {
            canvas.drawText(text, widhView - width, y, mTextPaint);
        } else {
            canvas.drawText(text, x, y, mTextPaint);
        }

    }

    public void drawTextCenterPostittionCustomColor(Canvas canvas, int x, int y, int colorText, String text, boolean isCenterX, boolean isCenterY, boolean isBold) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int currentColor = mTextPaint.getColor();
        mTextPaint.setColor(colorText);
        if (isBold) {
            mTextPaint.setFakeBoldText(true);
        }
        int width = bounds.width();
        int height = bounds.height();
        CharSequence str = TextUtils.ellipsize(text,
                (TextPaint) mTextPaint, getWidth(),
                TextUtils.TruncateAt.END);
        if (isCenterX && isCenterY) {
            canvas.drawText(str, 0, str.length(), x + width / 2f, y + height / 2f, mTextPaint);
        } else if (isCenterX) {
            canvas.drawText(str, 0, str.length(), x + width / 2f, y, mTextPaint);
        } else if (isCenterY) {
            canvas.drawText(str, 0, str.length(), x, y + height / 2f, mTextPaint);
        } else {
            canvas.drawText(str, 0, str.length(), x, y, mTextPaint);
        }
        //reset paint
        mTextPaint.setColor(currentColor);
        mTextPaint.setFakeBoldText(false);
    }

    public void drawRectangleWithColorAndBoderRadius(Canvas canvas, int color, Rect rect) {
        mPaintColumn.setStyle(Paint.Style.FILL);
        mPaintColumn.setColor(color);
        //draw rectange boder radius
        float boderRadius = (rect.right - rect.left) / 2f;
        float[] cornersTop = new float[]{
                boderRadius, boderRadius,        // Top left radius in px
                boderRadius, boderRadius,    // Top right radius in px
                0, 0,          // Bottom right radius in px
                0, 0           // Bottom left radius in px
        };
        final Path path = new Path();
        path.addRoundRect(new RectF(rect.left, rect.top, rect.right, rect.bottom), cornersTop, Path.Direction.CW);
        canvas.drawPath(path, mPaintColumn);
    }

    public void drawText(Canvas canvas, int x, int y, String text) {
        canvas.drawText(text, x, y, mTextPaint);
    }

    public void drawLine(Canvas canvas, int startX, int startY, int endX, int endY) {
        mPaintColumn.setAlpha((int) (0.5f * 255));
        int currentColor = mPaintColumn.getColor();
        mPaintColumn.setColor(colorLine);
        canvas.drawLine(startX, startY, endX, endY, mPaintColumn);
        mPaintColumn.setAlpha(255);
        mPaintColumn.setColor(currentColor);
    }

    public String ConvertTimeToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("đ/MM/yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        return formatter.format(date);
    }
}
