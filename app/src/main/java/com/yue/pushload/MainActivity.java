package com.yue.pushload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yue.pushload.activity.SimpleTest01Activity;
import com.yue.pushload.activity.SimpleTest02Activity;
import com.yue.pushload.activity.SimpleTest03Activity;
import com.yue.pushload.activity.SimpleTest04Activity;
import com.yue.pushload.activity.SimpleTest05Activity;
import com.yue.pushload.activity.SimpleTest07Activity;
import com.yue.pushload.activity.SimpleTestActivity;
import com.yue.pushload.activity02.Test01Activity;
import com.yue.pushload.activity02.Test02Activity;
import com.yue.pushload.activity02.Test03Activity;
import com.yue.pushload.activity02.Test04Activity;
import com.yue.pushload.activity02.Test05Activity;

/**
 * @ClassName:MainActivity
 * @auther:shimy
 * @date:2017/11/1 0001 上午 8:00
 * @description: 下拉刷新
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onclick();
        onclick01();
    }


    private void onclick() {
        findViewById(R.id.btn_main_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //scrollto scrollby
                startActivity(new Intent(MainActivity.this, SimpleTestActivity.class));
            }
        });

        findViewById(R.id.btn_main_simple02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过scroller实现滑动
                startActivity(new Intent(MainActivity.this, SimpleTest01Activity.class));
            }
        });

        findViewById(R.id.btn_main_simple03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过动画实现
                startActivity(new Intent(MainActivity.this, SimpleTest02Activity.class));
            }
        });

        findViewById(R.id.btn_main_simple04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过handle view或thead 进行延迟加载策略
                startActivity(new Intent(MainActivity.this, SimpleTest03Activity.class));
            }
        });

        findViewById(R.id.btn_main_simple05).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //view的事件分发机制
                startActivity(new Intent(MainActivity.this, SimpleTest04Activity.class));
            }
        });

        findViewById(R.id.btn_main_simple06).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleTest05Activity.class));
            }
        });

        findViewById(R.id.btn_main_simple07).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleTest07Activity.class));
            }
        });
    }

    private void onclick01() {
        findViewById(R.id.btn_main_layout01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test01Activity.class));
            }
        });
        findViewById(R.id.btnmain_layout02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test02Activity.class));
            }
        });
        findViewById(R.id.btnmain_layout03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test03Activity.class));
            }
        });
        findViewById(R.id.btnmain_layout04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test04Activity.class));
            }
        });
        findViewById(R.id.btnmain_layout05).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Test05Activity.class));
            }
        });
    }
}
