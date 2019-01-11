package com.imdevil.viewbase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

public class MyView extends View {

    private float mLastX;
    private float mLastY;
    private float x;
    private float y;
    private Scroller scroller;

    public MyView(Context context) {
        super(context);
        mLastX = getX();
        mLastY = getY();
        scroller = new Scroller(context);

    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        scroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //paint.setStrokeWidth(10f);
        int height = getHeight();
        int width = getWidth();
        canvas.drawCircle(width/2,height/2,width/2, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getRawX();
        y = event.getRawY();
        Log.d("My View","mLastX = "+mLastX);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("My View","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:

                Log.d("My View","ACTION_MOVE");
                float deltaX = x - mLastX;
                float deltaY = y - mLastY;
                float translationX = getTranslationX() + deltaX;
                Log.d("translationX","translationX ="+translationX);
                float translationY = getTranslationY() + deltaY;
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

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    public void  smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;
        int scrollY = getScrollY();
        int deltaY = destY - scrollY;
        scroller.startScroll(scrollX,scrollY,deltaX,deltaY,1000);
        invalidate();
    }
}
