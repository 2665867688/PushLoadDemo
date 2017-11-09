package com.yue.pushload.activity;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yue.pushload.R;

/**
 * @ClassName:SimpleTestActivity
 * @auther:shimy
 * @date:2017/11/1 0001 上午 8:18
 * @description: 测试一下实现原理
 */
public class SimpleTestActivity extends AppCompatActivity {

    private TextView mTvScroll;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test);
        initView();
    }

    private void initView(){
        mTvScroll = findViewById(R.id.tv_simple_scroll);
        findViewById(R.id.btn_simple_scrollto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvScroll.scrollTo(10,0);
            }
        });

        findViewById(R.id.btn_simple_scrollby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvScroll.scrollBy(10,0);
            }
        });

        findViewById(R.id.btn_simple_offset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(mTvScroll,100);
            }
        });
        
        findViewById(R.id.tv_simple_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleTestActivity.this, "哈哈哈", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_simple_translate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mTvScroll.setTranslationY(100);
            }
        });
    }
}
