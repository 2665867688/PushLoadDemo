package com.yue.pushload.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/1 0001.
 */

public class ScrollerTestView extends android.support.v7.widget.AppCompatTextView {

    private Scroller mScroller;

    public ScrollerTestView(Context context) {
        super(context);
        initView();
    }

    public ScrollerTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScrollerTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        mScroller = new Scroller(getContext());
    }


    public void smoothScrollTo(int x, int y) {
        int scrollX = getScrollX();
        int dex = x - scrollX;
        mScroller.startScroll(scrollX, 0, dex, 0, 1000);
        invalidate();
    }

    /**
     * 当veiw重绘是会调用这个方法
     */
    @Override
    public void computeScroll() {
        /*computeScrollOffset 当mscroller 计算滑动结束时会调用这个方法*/
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
