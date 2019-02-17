package com.imdevil.viewbase;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class TextViewGroup extends ViewGroup {

    private String[] strings;

    public TextViewGroup(Context context) {
        super(context);
    }

    public TextViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }
}
