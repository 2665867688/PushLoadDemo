package com.yue.pushload.view02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class TextTestView extends android.support.v7.widget.AppCompatTextView {
    public TextTestView(Context context) {
        super(context);
    }

    public TextTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        boolean ontouchevent = false;
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                ontouchevent = true;
//                break;
//            default:
//                ontouchevent = false;
//                break;
//        }
//        return ontouchevent;
//    }
}
