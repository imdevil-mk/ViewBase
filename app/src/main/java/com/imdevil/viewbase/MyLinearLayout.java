package com.imdevil.viewbase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(@NonNull Context context) {
        super(context);
    }

    public MyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MyFrameLayout","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("MyFrameLayout","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyFrameLayout","ACTION_UP");
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MyFrameLayout","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("MyFrameLayout","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MyFrameLayout","ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
