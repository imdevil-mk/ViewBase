package com.imdevil.viewbase.trash;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.imdevil.viewbase.R;

import java.util.List;

public class Picker extends View {

    private List<String> dataList;
    private int mTextSize;
    private Paint mPaint;
    private int mPaintColor;
    private int mVisibleTextCount;
    private int mTextMaxWidth;
    private int mTextMaxHeight;
    private int mDrawY;

    public Picker(Context context) {
        this(context,null);
    }
    public Picker(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Picker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs,defStyleAttr);
    }

    private void initAttrs(Context context,AttributeSet attrs, int defStyleAttr) {
        //Todo 解析xml属性
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.Picker);
        mPaintColor = a.getColor(R.styleable.Picker_textColor,Color.BLACK);
        mTextSize = a.getInt(R.styleable.Picker_textSize,15);
        mPaint = new Paint();
        mPaint.setColor(mPaintColor);
        mPaint.setTextSize(mTextSize);
        mVisibleTextCount = a.getInt(R.styleable.Picker_visibleTextCount,3);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (dataList.size() == 0){

        }else if (dataList.size() < 3){

        }else{
            measureTextSize();
            int width = mTextMaxWidth;
            int height = mTextMaxHeight * mVisibleTextCount;
            setMeasuredDimension(measureSize(widthSpecMode,widthSpecSize,width),
                    measureSize(heightSpecMode,heightSpecSize,height));
        }
    }

    private int measureSize(int specMode,int specSize,int size){
        if (specMode == MeasureSpec.EXACTLY){
            return specSize;
        }else {
            return Math.min(specSize,size);
        }
    }

    private void measureTextSize(){
        Paint.FontMetrics pf = mPaint.getFontMetrics();
        mTextMaxHeight = (int)(pf.bottom - pf.top);
        mDrawY = mTextMaxHeight;
        //TODO 这里存疑
        mTextMaxWidth = (int)mPaint.measureText(dataList.get(0).toString());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0;i < mVisibleTextCount;i++){
            canvas.drawText(dataList.get(i).toString(),0,mDrawY,mPaint);
            mDrawY += mTextMaxHeight;
        }
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }
}
