package com.yue.pushload.header;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yue.pushload.R;
import com.yue.pushload.pushload.RefreshHeader;
import com.yue.pushload.utils.DensityUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @ClassName:TestHeader
 * @auther:shimy
 * @date:2017/11/9 0009 下午 2:03
 * @description: 自定义下拉头测试
 */

public class TestHeader extends RelativeLayout implements RefreshHeader {

    public static String REFRESH_HEADER_PULLDOWN = "下拉可以刷新";
    public static String REFRESH_HEADER_REFRESHING = "正在刷新...";
    public static String REFRESH_HEADER_LOADING = "正在加载...";
    public static String REFRESH_HEADER_RELEASE = "释放立即刷新";
    public static String REFRESH_HEADER_FINISH = "刷新完成";
    public static String REFRESH_HEADER_FAILED = "刷新失败";
    public static String REFRESH_HEADER_LASTTIME = "上次更新 M-d HH:mm";

    protected String KEY_LAST_UPDATE_TIME = "LAST_UPDATE_TIME";

    protected TextView mTitleText;
    protected TextView mLastUpdateText;
    protected ImageView mArrowView;
    protected ImageView mProgressView;
    protected DateFormat mFormat = new SimpleDateFormat(REFRESH_HEADER_LASTTIME, Locale.CHINA);

    public TestHeader(Context context) {
        super(context);
        initView(context);
    }

    public TestHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TestHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public View getView() {
        return this;
    }

    private void initView(Context context) {
        DensityUtil density = new DensityUtil();

        LinearLayout layout = new LinearLayout(context);
        layout.setId(android.R.id.widget_frame);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setOrientation(LinearLayout.VERTICAL);
        mTitleText = new TextView(context);
        mTitleText.setText(REFRESH_HEADER_PULLDOWN);
        mTitleText.setTextColor(0xff666666);

        mLastUpdateText = new TextView(context);
        mLastUpdateText.setTextColor(0xff7c7c7c);
        mLastUpdateText.setText(mFormat.format(new Date()));
        LinearLayout.LayoutParams lpHeaderText = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layout.addView(mTitleText, lpHeaderText);
        LinearLayout.LayoutParams lpUpdateText = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        layout.addView(mLastUpdateText, lpUpdateText);

        LayoutParams lpHeaderLayout = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpHeaderLayout.addRule(CENTER_IN_PARENT);
        addView(layout, lpHeaderLayout);

        LayoutParams lpArrow = new LayoutParams(density.dip2px(20), density.dip2px(20));
        lpArrow.addRule(CENTER_VERTICAL);
        lpArrow.addRule(LEFT_OF, android.R.id.widget_frame);
        mArrowView = new ImageView(context);
        mArrowView.setImageResource(R.drawable.ic_back);
        addView(mArrowView, lpArrow);

        LayoutParams lpProgress = new LayoutParams((ViewGroup.LayoutParams) lpArrow);
        lpProgress.addRule(CENTER_VERTICAL);
        lpProgress.addRule(LEFT_OF, android.R.id.widget_frame);
        mProgressView = new ImageView(context);
        mProgressView.animate().setInterpolator(new LinearInterpolator());
        addView(mProgressView, lpProgress);

        if (isInEditMode()) {
            mArrowView.setVisibility(GONE);
            mTitleText.setText(REFRESH_HEADER_REFRESHING);
        } else {
            mProgressView.setVisibility(GONE);
        }
    }
}
