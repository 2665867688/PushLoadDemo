package com.yue.pushload.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * 
 * @ClassName:PullableScrollView
 * @author: shimy
 * @date: 2017/4/5 0005 下午 3:15
 * @description: 下拉刷新scrollview
 */

public class PullableScrollView extends ScrollView implements Pullable{
    public PullableScrollView(Context context) {
        super(context);
    }

    public PullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean canPullUp() {

        View childView = getChildAt(0);

        if (childView!=null&&childView.getMeasuredHeight() <= getScrollY() + getHeight()){
            return true;
        }
        return false;
    }
}
