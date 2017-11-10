package com.yue.pushload.activity02;

import android.animation.ValueAnimator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yue.pushload.R;

/**
 * @ClassName:Test04Activity
 * @auther:shimy
 * @date:2017/11/8 0008 下午 3:26
 * @description: 刷新 加载 参考资料 ：PullToRefreshLayout和smartRefreshLayout
 */
public class Test04Activity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ValueAnimator valueAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test04);
        initView();
    }

    private void initView() {
    }
}
