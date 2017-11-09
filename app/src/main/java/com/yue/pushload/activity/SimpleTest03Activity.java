package com.yue.pushload.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yue.pushload.R;

/**
 * @ClassName:SimpleTest03Activity
 * @auther:shimy
 * @date:2017/11/2 0002 上午 9:39
 * @description: 延迟策略实现弹性滑动
 */
public class SimpleTest03Activity extends AppCompatActivity {

    private RelativeLayout mLayout;
    private int mCount = 0;
    private static final int MESSAGE_SCROLL_TO = 100;
    private static final int MESSAGE_SCROLL_TO_BACK = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple03);
        initView();
    }

    private void initView() {
        mLayout = findViewById(R.id.layout_simple03);
        findViewById(R.id.btn_simple03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleTest03Activity.this, "开始", Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessage(MESSAGE_SCROLL_TO);
            }
        });
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO://下滑
                    mCount--;
                    if (mCount > -100) {
                        mLayout.scrollTo(0, mCount);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, 50);
                    } else {
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO_BACK, 50);
                    }
                    break;
                case MESSAGE_SCROLL_TO_BACK://回去
                    mCount++;
                    if (mCount < 0) {
                        mLayout.scrollTo(0, mCount);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO_BACK, 50);
                    }
                    break;
            }
        }
    };
}
