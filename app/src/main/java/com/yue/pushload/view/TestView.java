package com.yue.pushload.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/11/1 0001.
 */

public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int mLeft;

    private int mRright;

    private int mTop;

    private int mBottom;


    private void initView() {
        mLeft = getLeft();
        mRright = getRight();
        mTop = getTop();
        mBottom = getBottom();

        int[] position = new int[2];
        getLocationOnScreen(position);

//        VelocityTracker velocityTracker = VelocityTracker.obtain();
//        //添加滑动点击事件 可以在onTouchEvent方法里
//        velocityTracker.addMovement(event);
//        //设置时间 即通过1000毫秒内滑动的像素数来计算速度，计算速度可以为负数
//        velocityTracker.computeCurrentVelocity(1000);
//        //获取相对于x轴和y轴的速度
//        int xVelocity = (int) velocityTracker.getXVelocity();
//        int yVelocity = (int) velocityTracker.getYVelocity();
//
//        velocityTracker.clear();
//        velocityTracker.recycle();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
