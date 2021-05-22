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
import com.example.basejavaandroid.model.BmiHistoryRes;

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
    Paint mPaint;
    Paint mPaintDrawRectangleScore;
    Paint mTextPaint;
    int numberColumnRec = 0;
    String[] listDescribe = new String[]{getResources().getString(R.string.thieucan), getResources().getString(R.string.nomarl), getResources().getString(R.string.thuacan), getResources().getString(R.string.beophi)};
    int[] listColor = new int[]{getResources().getColor(R.color.lifestyle_blue), getResources().getColor(R.color.green), getResources().getColor(R.color.yellow), getResources().getColor(R.color.red)};
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
        }
        numberColumnRec = this.listHistory.size();
        invalidate();
    }

    private void initPaint() {
        mPaint = new Paint();
        mTextPaint = new Paint();
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(colorText);
        mTextPaint.setTextSize(textSize);

        mPaintDrawRectangleScore = new Paint();
        mPaintDrawRectangleScore.setStyle(Paint.Style.FILL);
        mPaintDrawRectangleScore.setColor(DEFAULT_COLOR_TEXT);
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
        heightView = widhView;
        setMeasuredDimension(widthSize, (int) heightView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        widhView = getWidth();
        heightView = getHeight();
        dx = widhView / 14;
        dy = heightView / 8;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTextScoreBMIX(canvas);
        drawTextTime(canvas);
        drawBeakPoint(canvas);
    }

    private void drawTextScoreBMIX(Canvas canvas) {
        int numberBreakpoint = 7;
        int index = 0;

        for (int i = 0; i < numberBreakpoint; i++) {
            Rect bounds = new Rect();
            mTextPaint.getTextBounds(String.valueOf(index), 0, String.valueOf(index).length(), bounds);
            int width = bounds.width();
            if (i == 0) {
                drawTextCenterPostition(canvas, (int) (dx / 2 - width), (int) (heightView - (dy * (i + 1))), String.valueOf(index), true, false);
                drawLine(canvas, (int) dx, (int) (heightView - (dy * (i + 1))), (int) widhView, (int) (heightView - (dy * (i + 1))));

            } else if (i == numberBreakpoint - 1) {
                drawTextCenterPostition(canvas, (int) (dx / 2 - width), (int) (heightView - (dy * (i + 1))), String.valueOf(index), true, true);
                drawLine(canvas, (int) dx, (int) (heightView - (dy * (i + 1))), (int) widhView, (int) (heightView - (dy * (i + 1))));
            } else {
                drawTextCenterPostition(canvas, (int) (dx / 2 - width), (int) (heightView - (dy * (i + 1))), String.valueOf(index), true, true);
                drawLine(canvas, (int) dx, (int) (heightView - (dy * (i + 1))), (int) widhView, (int) (heightView - (dy * (i + 1))));
            }
            index += 5;
        }
    }

    private void drawTextTime(Canvas canvas) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(String.valueOf(R.string.bmi), 0, 3, bounds);
        int height = bounds.height();
        int yTime = (int) (heightView - dy / 2 - height / 2f);
        for (int i = 1; i <= listHistory.size(); i++) {
            mTextPaint.getTextBounds(String.valueOf(listHistory.get(i - 1).getScore()), 0, String.valueOf(listHistory.get(i - 1).getScore()).length(), bounds);
            drawRectangleWithColor(canvas, (int) ((2 * i - 1) * dx), (float) listHistory.get(i - 1).getScore());
            drawTextTime(canvas, (int) ((2 * i - 1) * dx), yTime, ConvertTimeToString(listHistory.get(i - 1).getUpdateAt()), false, false);
            drawScoreBMI(canvas, mTextPaint, (int) ((2 * i - 1) * dx + dx / 2f), (int) (heightView - dy - dy / 2f), ConvertFloatToString((float) listHistory.get(i - 1).getScore()), textSize * 1.2f, true, Color.WHITE);
        }

    }

    public String ConvertFloatToString(float number) {
        int frontDot = (int) number;
        float afterDot = number - frontDot;
        if (afterDot == 0) {
            return String.valueOf(frontDot);
        }
        return String.format("%.1f", number).replace(',', '.');
    }

    public void drawBeakPoint(Canvas canvas) {
        int yDescribe = (int) (heightView - dy / 4f);
        int d = (int) (widhView / 4f);
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(String.valueOf(R.string.thieucan), 0, 1, bounds);
        int height = bounds.height();
        for (int i = 0; i < 4; i++) {
            drawCirclePosWithColor(canvas, (int) ((i) * d + (dy / 2f)), yDescribe, listColor[i]);
            drawTextCenterPostition(canvas, (int) ((i) * d + (dy / 2f)) + (int) (dy / 5), (int) (yDescribe + height / 2f), listDescribe[i], false, false);
        }
    }

    public void drawCirclePosWithColor(Canvas canvas, int x, int y, int color) {
        int currentColor = mPaint.getColor();
        mPaint.setColor(color);
        int radiusCircle = (int) (dy / 10);
        canvas.drawCircle(x, y, radiusCircle, mPaint);
        mPaint.setColor(currentColor);

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
        float heightRectangle = (scoreBMI * dy) / 5f;
        // fill
        mPaintDrawRectangleScore.setStyle(Paint.Style.FILL);
        mPaintDrawRectangleScore.setColor(colorRec);
        Rect rect = new Rect(left, (int) ((heightView - dy - heightRectangle)), (int) (left + dx), (int) (heightView - dy));
        canvas.drawRect(rect, mPaintDrawRectangleScore);
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
       /* Typeface plain = ResourcesCompat.getFont(getContext(), R.font.montserrat);
        mTextPaint.setTypeface(plain);*/
        canvas.drawText(str, 0, str.length(), x - width / 2f, y + height / 2f, mTextPaint);
        mTextPaint.setTextSize(currentSize);
        mTextPaint.setColor(currentColor);
        /* mTextPaint.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat));*/
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

    public void drawText(Canvas canvas, int x, int y, String text) {
        canvas.drawText(text, x, y, mTextPaint);
    }

    public void drawLine(Canvas canvas, int startX, int startY, int endX, int endY) {
        mPaint.setAlpha((int) (0.5f * 255));
        int currentColor = mPaint.getColor();
        mPaint.setColor(colorLine);
        canvas.drawLine(startX, startY, endX, endY, mPaint);
        mPaint.setAlpha(255);
        mPaint.setColor(currentColor);
    }

    public String ConvertTimeToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
