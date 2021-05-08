package com.example.basejavaandroid.base;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;

public class CanvasUtils {
    public static void drawRectangleAndBoderRadius(Canvas canvas,Paint mPaint, Rect rect,float radius,boolean isLeft){
        //draw rectange boder radius
       // float boderRadius = (rect.bottom - rect.top)/2;
        float boderRadius =  radius;
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
        if(isLeft){
            path.addRoundRect(new RectF(rect.left,rect.top,rect.right,rect.bottom), cornersLeft, Path.Direction.CW);
        }else{
            path.addRoundRect(new RectF(rect.left,rect.top,rect.right,rect.bottom), cornersRight, Path.Direction.CW);
        }
        canvas.drawPath(path, mPaint);
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
    public void drawTriangleWithColor(Canvas canvas,Paint mPaint,  int x, int y, int width) {

        int halfWidth = width/2;
        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();
        canvas.drawPath(path, mPaint);
    }
}
