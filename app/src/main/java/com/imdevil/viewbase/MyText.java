package com.imdevil.viewbase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyText extends View {

    private Paint mPaint;
    private float height;
    private float width;
    private float baseLineX;
    private float baseLineY;
    private float top;
    private float bottom;
    private float ascent;
    private float descent;

    public MyText(Context context) {
        this(context,null);
    }

    public MyText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setTextSize(250);
        Paint.FontMetrics pf = mPaint.getFontMetrics();
        top = pf.top;
        ascent = pf.ascent;
        descent = pf.descent;
        bottom = pf.bottom;
    }

    public MyText(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GRAY);
        String s = "giPpz中";
        canvas.drawText(s,0,baseLineY,mPaint);
        Rect textRect = new Rect();
        mPaint.getTextBounds(s,0,s.length(),textRect);

        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0,0,0,width,mPaint);
        canvas.drawLine(0,height,width,height,mPaint);
        baseLineY = height/2;
        mPaint.setColor(Color.RED);
        canvas.drawLine(0,baseLineY,width,baseLineY,mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0,baseLineY+top,width,baseLineY+top,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0,baseLineY+ascent,width,baseLineY+ascent,mPaint);
        canvas.drawLine(0,baseLineY+textRect.top,width,baseLineY+textRect.top,mPaint);
        canvas.drawLine(0,baseLineY+textRect.bottom,width,baseLineY+textRect.bottom,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0,baseLineY+descent,width,baseLineY+descent,mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0,baseLineY+bottom,width,baseLineY+bottom,mPaint);
        Log.d("tag","textHeight is "+(textRect.bottom-textRect.top));
        Log.d("tag","textRect.bottom is"+textRect.bottom);
        Log.d("tag","textRect.top is"+textRect.top);





    }
}
