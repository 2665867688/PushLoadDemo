package com.yue.pushload.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yue.pushload.R;

/**
 * @ClassName:Simple05Activity
 * @auther:shimy
 * @date:2017/11/3 0003 下午 3:54
 * @description: 事件分发测试
 */
public class SimpleTest05Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple05);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(this, "activity处理"+event.getAction(), Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
