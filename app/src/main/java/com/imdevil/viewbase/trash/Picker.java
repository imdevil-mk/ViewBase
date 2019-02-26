package com.imdevil.viewbase.trash;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.MotionEvent;
import android.view.View;

import com.imdevil.viewbase.R;

import java.util.List;

public class Picker extends View {

    private final String TAG = "Picker ";
    private List<String> dataList;
    private int mTextSize;
    private Paint mPaint;
    private int mPaintColor;
    private int mVisibleTextCount;
    private int mTextMaxWidth;
    private int mTextMaxHeight;
    private int mDrawY;
    private float mDrawOffsetY;
    private float mMaxDrawOffsetY;
    private float mMinDrawOffsetY;
    private float mLastDownY;
    private float mScrollOffsetY;
    private float mTotalScrollOffsetY;
    private int mCurrentItem;
    private int mDefaultItem;
    private boolean isFirstDraw = true;
    private boolean isShowCurtain = true;

    public Picker(Context context) {
        this(context, null);
    }

    public Picker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Picker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG+" method", "Picker: ");
        initAttrs(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        //Todo 解析xml属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Picker);
        mPaintColor = a.getColor(R.styleable.Picker_textColor, Color.BLACK);
        mTextSize = a.getInt(R.styleable.Picker_textSize, 15);
        mPaint = new Paint();
        mPaint.setColor(mPaintColor);
        mPaint.setTextSize(mTextSize);
        mVisibleTextCount = a.getInt(R.styleable.Picker_visibleTextCount, 3);
        a.recycle();
    }

    private void init() {
        measureTextSize();
        //Todo
        mDefaultItem = 1;
        mCurrentItem = mDefaultItem;
        mMaxDrawOffsetY = mCurrentItem * mTextMaxHeight;
        Log.d(TAG, "setDataList: mMaxDrawOffsetY " + mMaxDrawOffsetY);
        mMinDrawOffsetY = -(dataList.size()- 1 - mCurrentItem) * mTextMaxHeight;
        Log.d(TAG, "setDataList: mMinDrawOffsetY " + mMinDrawOffsetY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG+" method", "onMeasure: ");
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (dataList.size() == 0) {

        } else if (dataList.size() < 3) {

        } else {
            int width = mTextMaxWidth;
            int height = mTextMaxHeight * mVisibleTextCount;
            setMeasuredDimension(measureSize(widthSpecMode, widthSpecSize, width),
                    measureSize(heightSpecMode, heightSpecSize, height));
        }
    }

    private int measureSize(int specMode, int specSize, int size) {
        /*if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            return Math.min(specSize, size);
        }*/
        return Math.min(specSize, size);
    }

    private void measureTextSize() {
        Paint.FontMetrics pf = mPaint.getFontMetrics();
        mTextMaxHeight = (int) (pf.bottom - pf.top);
        mDrawY = mTextMaxHeight;
        //TODO 这里存疑
        mTextMaxWidth = (int) mPaint.measureText(dataList.get(20).toString());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG+" method", "onLayout: ");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG+" method", "onDraw: ");
        super.onDraw(canvas);

        if (isShowCurtain){
            Rect rect = new Rect(0,mTextMaxHeight,mTextMaxWidth,2 * mTextMaxHeight);
            mPaint.setColor(Color.GRAY);
            canvas.drawRect(rect,mPaint);
        }
        mPaint.setColor(mPaintColor);

        if(mTotalScrollOffsetY > mMaxDrawOffsetY){
            mTotalScrollOffsetY = mMaxDrawOffsetY;
            Log.d(TAG, "A ");
        }
        if (mTotalScrollOffsetY < mMinDrawOffsetY){
            mTotalScrollOffsetY = mMinDrawOffsetY;
            Log.d(TAG, "B ");
        }

        mCurrentItem = mDefaultItem + (int)(-mTotalScrollOffsetY / mTextMaxHeight);
        mDrawOffsetY = -mTotalScrollOffsetY % mTextMaxHeight;
        Log.d(TAG, "onDraw: mCurrentItem is "+mCurrentItem);
        for (int i = 0; i < mVisibleTextCount+2; i++) {
            int n = mCurrentItem - 2 + i;
            String s = "";
            if (n >= 0 && n < dataList.size()){
                s = dataList.get(n);
            }
            Rect textRect = new Rect();
            mPaint.getTextBounds(s,0,s.length(),textRect);
            float textHeight = textRect.bottom - textRect.top;
            float textWidth = textRect.right - textRect.left;
            canvas.drawText(s, (mTextMaxWidth-textWidth)/2, (i * mTextMaxHeight + mDrawOffsetY)-((mTextMaxHeight-textHeight)/2)-textRect.bottom, mPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_DOWN");
                mLastDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: ACTION_UP");
                mScrollOffsetY = event.getY() - mLastDownY;
                mTotalScrollOffsetY += mScrollOffsetY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }

    public void setDataList(List<String> dataList) {
        Log.d(TAG+" method", "setDataList: ");
        this.dataList = dataList;
        init();
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }
}
