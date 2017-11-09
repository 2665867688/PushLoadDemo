package com.yue.pushload.activity02;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yue.pushload.R;

public class Test01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test01);
        initView();
    }

    private void initView() {
        TextView mTvShow = findViewById(R.id.tv_text01_show);
        int userVersion = 0;
        int STATUS_BAR_HEIGHT = 0;
        if (Build.VERSION.SDK_INT >= 21) {
//            if (userVersion >= 5) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            }
        }
        if (!TextUtils.isEmpty(Build.VERSION.RELEASE)&&Build.VERSION.RELEASE.length()>0) {
            userVersion = Integer.parseInt(Build.VERSION.RELEASE.substring(0,1));
        }
        if (STATUS_BAR_HEIGHT == 0) {
            //获取系统状态栏高度
            //获取status_bar_height资源的ID
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                STATUS_BAR_HEIGHT = getResources().getDimensionPixelSize(resourceId);
            }
        }
        mTvShow.setText(Build.VERSION.SDK_INT + "\n" + Build.VERSION.RELEASE+"\nuserversion:"+userVersion+"\nstatusbar:"+STATUS_BAR_HEIGHT);

    }
}
