package com.yue.pushload.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yue.pushload.R;
import com.yue.pushload.view.ScrollerTestView;
/**
 * @ClassName:SimpleTest01Activity
 * @auther:shimy
 * @date:2017/11/2 0002 上午 8:30
 * @description: 通过Scroller实现弹性滑动
 */
public class SimpleTest01Activity extends AppCompatActivity {

    private ScrollerTestView mScrollerView;
    private int movex = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test01);
        onclick();
    }

    private void onclick() {
        mScrollerView = findViewById(R.id.scroller_simple01);
        findViewById(R.id.btn_simple01_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movex +=120;
                Toast.makeText(SimpleTest01Activity.this, "滑动"+movex, Toast.LENGTH_SHORT).show();
                mScrollerView.smoothScrollTo(-movex,0);
            }
        });

    }
}
