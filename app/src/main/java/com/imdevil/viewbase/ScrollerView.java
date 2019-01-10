package com.imdevil.viewbase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

public class ScrollerView extends View {

    Context context;

    Scroller scroller = new Scroller(context);

    public ScrollerView(Context context) {
        super(context);
        this.context = context;
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setStrokeWidth(10f);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200,200,40,paint);
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){

    }

}
