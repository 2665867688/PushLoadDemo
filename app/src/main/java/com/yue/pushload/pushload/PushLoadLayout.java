package com.yue.pushload.pushload;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import java.util.logging.Handler;

/**
 * @ClassName:PushLoadLayout
 * @auther:shimy
 * @date:2017/11/8 0008 下午 3:44
 * @description: 上拉加载 下拉刷新
 */

public class PushLoadLayout extends RelativeLayout {

    // 初始状态
    public static final int INIT = 0;
    // 释放刷新
    public static final int RELEASE_TO_REFRESH = 1;
    // 正在刷新
    public static final int REFRESHING = 2;
    // 释放加载
    public static final int RELEASE_TO_LOAD = 3;
    // 正在加载
    public static final int LOADING = 4;
    // 操作完毕
    public static final int DONE = 5;
    // 当前状态
    private int state = INIT;
    /**
     * 手指按下是的触控坐标
     */
    private int mDownX;
    private int mDownY;

    /**
     * 记录上一次手指在屏幕上的触控坐标
     */
    private int mLastX;
    private int mLastY;
    // 过滤多点触碰
    private int mEvents = 0;

    //弹性滚动
    private Scroller mScroller;
    //计算速度
    private VelocityTracker mVelocityTracker;

    /**
     * handler 发送消息 暂时用不到
     */
    private Handler mHandler;
    /**
     * 下拉头部视图
     */
    protected RefreshHeader mRefreshHeader;
    /**
     * 上拉底部视图
     */
    protected RefreshFooter mRefreshFooter;
    /**
     * 显示内容视图
     */
    protected RefreshContent mRefreshContent;

    /**
     * 是否开启纯滚动模式
     * 即没有头和尾的方式，单纯的滚动
     */
    protected boolean mEnablePureScrollMode = false;

    // 这两个变量用来控制pull的方向，如果不加控制，当情况满足可上拉又可下拉时没法下拉
    private boolean canPullDown = true;
    private boolean canPullUp = true;
    // 下拉的距离。注意：pullDownY和pullUpY不可能同时不为0
    public float pullDownY = 0;
    // 上拉的距离
    private float pullUpY = 0;
    /**
     * 手指滑动距离与下拉头的滑动距离比，中间会随正切函数变化
     * 造成用力拉的效果
     */
    private float radio = 2;

    /**
     * 最大拖动距离
     */
    private int maxDistance = 500;

    private float headerFirstHeight = 0;

    public PushLoadLayout(Context context) {
        super(context);
        initView();
    }

    public PushLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PushLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        if (mScroller == null) {
            mScroller = new Scroller(getContext());
        }
    }

    /**
     * 此布局（PushLoadLayout）加载完成时执行此方法
     * 可以在此获取到子view
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 3) {
            throw new RuntimeException(RuntimeException.class.getSimpleName() + ":最多只能放三个子view");
        } else if (mEnablePureScrollMode && childCount > 1) {
            throw new RuntimeException(RuntimeException.class.getSimpleName() + ":纯滚动模式只能放一个view，请不要防止header和footer");
        }

        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof RefreshHeader && mRefreshHeader == null) {
                mRefreshHeader = (RefreshHeader) view;
            } else if (view instanceof RefreshFooter && mRefreshFooter == null) {
                mRefreshFooter = (RefreshFooter) view;
            } else if (view instanceof RefreshContent && mRefreshContent == null) {
                mRefreshContent = (RefreshContent) view;
            }
        }
    }

    /**
     * 当view附加到窗体上时调用此方法
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) return;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();
                mLastX = (int) ev.getX();
                mLastY = (int) ev.getY();
                mScroller.forceFinished(true);
                mEvents = 0;
                canPullDown = true;
                canPullUp = true;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                //过滤多点触控问题
                mEvents = -1;
                break;
            case MotionEvent.ACTION_MOVE:
                /*
                 * 判断距离当竖直方向上的距离大于水平方向的距离时可以弹性滑动
                 */
                if (Math.abs(ev.getY() - mDownY) < Math.abs(ev.getX() - mDownX)) {
                    break;
                }

                if (mEvents == 0) {
                    if (mRefreshContent.canPullDown() ) {
                        //下拉状态
                        if (Math.abs(getScrollY()) < maxDistance) {
                            //可以刷新
                            pullDownY = (ev.getY() - mLastY) / radio;

                        /*
                         * 滚动 注意滚动的是内容 第二个参数y轴滚动的距离
                         * by与to相对
                         */
                            scrollBy(0, (int) -pullDownY);
                            mRefreshHeader.getView().requestLayout();
                        }
                    }else if (mRefreshContent.canPullUp()){
                        pullUpY = (ev.getY() - mLastY)/radio;
                        scrollBy(0, (int) -pullUpY);
                        mRefreshFooter.getView().requestLayout();
                    }
                    // 根据下拉距离改变比例 制造用力拉的效果
                    radio = (float) (2 + 2 * Math.tan(Math.PI / 2 / getMeasuredHeight() * (Math.abs(getScrollY()))));
                } else {
                    mEvents = 0;
                }

//                smoothScrollBy(0, -pullDownY);
                break;
            case MotionEvent.ACTION_UP:
                //弹性回弹
                smoothScrollBy(0, -getScrollY());
                break;


        }
        mLastY = (int) ev.getY();

        //使用父类继续分发事件
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mRefreshHeader != null) {
            View headerView = mRefreshHeader.getView();
            int height = headerView.getMeasuredHeight();
            float mscrolly = Math.abs(getScrollY());
            headerView.layout(0, (int) -(height + mscrolly), headerView.getMeasuredWidth(), 0);
            headerView.scrollTo(0, (int) -mscrolly);
        }
        if (mRefreshContent != null) {
            View contentView = mRefreshContent.getView();
            contentView.layout(0, 0, contentView.getMeasuredWidth(), contentView.getMeasuredHeight());
        }

        if (mRefreshFooter != null) {
            int parentHeight = getMeasuredHeight();
            View footerView = mRefreshFooter.getView();
            Log.d("PushLoadLayout","parentHeight:"+parentHeight);
            int height = footerView.getMeasuredHeight();
            float mscrolly = Math.abs(getScrollY());
            footerView.layout(0, parentHeight,footerView.getMeasuredWidth(),(int) (parentHeight+height+mscrolly));
        }
    }

    private void smoothScrollBy(float dx, float dy) {
        mScroller.startScroll(0, getScrollY(), 0, (int) dy, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * 当view与窗体window解绑时调用
     * 可以再次清理资源
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        mVelocityTracker.recycle();
    }
}
