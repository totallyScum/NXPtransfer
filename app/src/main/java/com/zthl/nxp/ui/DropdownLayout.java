package com.zthl.nxp.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class DropdownLayout extends LinearLayout {
    private static final String TAG = "DropdownLayout";
    private int mDropSpeed ;
    private boolean isOpen = false;
    private View mDropView;
    private int mDropHeight;
    public DropdownLayout(Context context) {
        super(context);
    }

    public DropdownLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DropdownLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DropdownLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (null != this.getChildAt(1) && this.getChildAt(1) instanceof ViewGroup){
            ViewGroup rootDropView = (ViewGroup) this.getChildAt(1);
            int childCount = rootDropView.getChildCount();
            if (childCount>0){
                mDropView = rootDropView.getChildAt(0);
                mDropHeight = mDropView.getMeasuredHeight();
                Log.i(TAG,"onMeasure dropHeight:"+mDropHeight);
                mDropView.setY(-mDropHeight);
            }
        }
    }

    public void toggle(){
        if (isOpen){
            ObjectAnimator translationY = ObjectAnimator.ofFloat(mDropView, "translationY", 0, -mDropHeight);
            translationY.setDuration(mDropSpeed);
            translationY.start();
            isOpen = false;
        }else{
            ObjectAnimator translationY = ObjectAnimator.ofFloat(mDropView, "translationY", -mDropHeight, 0);
            translationY.setDuration(mDropSpeed);
            translationY.start();
            isOpen = true;
        }
    }
}
