package com.yue.pushload.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/11/3 0003.
 */

public class DispatchTestLayout extends FrameLayout{
    private final String TAG = getClass().getSimpleName();

    public DispatchTestLayout(@NonNull Context context) {
        super(context);
    }

    public DispatchTestLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchTestLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,TAG+"拦截"+super.onTouchEvent(event)+event.getAction());
        return super.onTouchEvent(event);
    }
}
