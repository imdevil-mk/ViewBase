package com.imdevil.viewbase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    private float mLastX;
    private float mLastY;
    private float x;
    private float y;

    public MyView(Context context) {
        super(context);
        mLastX = getX();
        mLastY = getY();

    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10f);
        canvas.drawCircle(0, 0, 50, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getRawX();
        y = event.getRawY();
        Log.d("ACTION_DOWN","mLastX = "+mLastX);
        Log.d("ACTION_DOWN","translationX = "+getTranslationX());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - mLastX;
                Log.d("ACTION_MOVE","deltaX = "+deltaX);
                float deltaY = y - mLastY;
                float translationX = getTranslationX() + deltaX;
                float translationY = getTranslationY() + deltaY;
                Log.d("translationX","translationX = "+translationX);
                setTranslationX(translationX);
                setTranslationY(translationY);

                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

}
