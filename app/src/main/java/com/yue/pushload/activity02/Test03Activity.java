package com.yue.pushload.activity02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yue.pushload.R;

/**
 * @ClassName:Test03Activity
 * @auther:shimy
 * @date:2017/11/8 0008 下午 1:25
 * @description: 继承ViewGroup 需要考虑子元素的margin和自身的padding值
 */
public class Test03Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test03);
        initView();
    }

    private void initView(){

    }
}
