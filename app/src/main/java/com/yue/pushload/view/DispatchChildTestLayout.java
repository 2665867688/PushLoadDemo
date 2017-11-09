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

public class DispatchChildTestLayout extends FrameLayout{
    private final String TAG = getClass().getSimpleName();

    public DispatchChildTestLayout(@NonNull Context context) {
        super(context);
    }

    public DispatchChildTestLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchChildTestLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,TAG+"子拦截"+super.onTouchEvent(event)+event.getAction());
        return true;
    }
}
