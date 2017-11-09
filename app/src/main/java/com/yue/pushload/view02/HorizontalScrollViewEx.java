package com.yue.pushload.view02;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @ClassName:HorizontalScrollViewEx
 * @date:2017/11/4 0004 上午 11:05
 * @description: 左右和上下的滑动冲突 内部拦截法
 */

public class HorizontalScrollViewEx extends ViewGroup {

    private final String TAG = getClass().getSimpleName();

    //子view的数量
    private int mChildrenSize;
    //子view的宽度
    private int mChildWidth;
    //子view的指针
    private int mChildIndex;

    //分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    //分别记录上次滑动的坐标 (onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    //弹性滑动 渐进式在一段时间内滑动一段距离
    private Scroller mScroller;
    //计算速度 （重点 - 起点）/时间
    private VelocityTracker mVelocityTracker;

    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }


    /**
     * 决定是否拦截事件
     *
     * @param ev
     * @return
     */

    private boolean mIntercepted = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //是否拦截的变量
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                if (!mScroller.isFinished()) {
                    //没有滑动完成，拦截down事件，防止子view接收到事件
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                Log.d(TAG, "intercepted=" + intercepted + "event.getx" + x + "|event.gety" + y + ":action:" + ev.getAction());
                //当x轴的滑动距离大于y轴的距离时，拦截事件
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
        }
        Log.d(TAG, "intercepted=" + intercepted + "event.getx" + x + "|event.gety" + y + ":action:" + ev.getAction());
        mLastXIntercept = x;
        mLastYIntercept = y;
        mIntercepted = intercepted;
        return intercepted;
    }


    /**
     * 决定是否消耗掉事件
     *
     * @param event
     * @return
     */
    int downx = 0;
    int downy = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downx = x;
                downy = y;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    scrollBy(-deltaX, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                int deltaX2 = x - downx;
                int deltaY2 = y - downy;
//                if (Math.abs(deltaX2) > Math.abs(deltaY2)) {
                    int scrollX = getScrollX();
                    int scrollToChildIndex = scrollX / mChildWidth;
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float xVelocity = mVelocityTracker.getXVelocity();
                    if (Math.abs(xVelocity) >= 50) {
                        mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                    } else {
                        mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                    }
                    mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
                    int dx = mChildIndex * mChildWidth - scrollX;
                    smoothScrollBy(dx, 0);
                    mVelocityTracker.clear();
//                }
                break;
        }
        Log.d(TAG, "ontouchevent=" + "event.getx" + x + "|event.gety" + y + ":action:" + event.getAction() + mIntercepted);
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 0;
        int measuredHeight = 0;
        final int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, childView.getMeasuredHeight());
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measuredWidth, heightSpaceSize);
        } else {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        mChildrenSize = childCount;
        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() != GONE) {
                final int childWidth = childView.getMeasuredWidth();
                mChildWidth = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth, childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }


    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}
