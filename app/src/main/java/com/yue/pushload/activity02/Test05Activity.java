package com.yue.pushload.activity02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yue.pushload.R;

public class Test05Activity extends AppCompatActivity {

    private RelativeLayout layout;
    private Button btn;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test05);
        layout = findViewById(R.id.layout_test05);
        btn = findViewById(R.id.btn_test05);
        findViewById(R.id.btn_test05).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i+=10;
                layout.layout(0,btn.getMeasuredHeight(),layout.getMeasuredWidth(),btn.getMeasuredHeight()+layout.getMeasuredHeight()+i);
                layout.scrollTo(0,-i);
            }
        });
    }
}
