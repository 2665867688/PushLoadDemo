package com.yue.pushload.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yue.pushload.R;

/**
 * @ClassName:SimpleTest02Activity
 * @auther:shimy
 * @date:2017/11/2 0002 上午 8:30
 * @description: 通过动画实现弹性滑动
 */
public class SimpleTest02Activity extends AppCompatActivity {

    private RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_test02);
        initView();
    }

    @SuppressLint("ObjectAnimatorBinding")
    private void initView() {
        mLayout = findViewById(R.id.layout_simple02);
        findViewById(R.id.btn_simple02_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mLayout, "translationY", 0, 300);
                objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                objectAnimator.setDuration(1000);

                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mLayout, "translationY", 300, 0);
                objectAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
                objectAnimator2.setDuration(1000);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(objectAnimator).before(objectAnimator2);
                animatorSet.start();
            }
        });


        findViewById(R.id.btn_simple02_start02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 200);
                valueAnimator.setDuration(1000);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                //监听动画每一帧的变化
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fa = animation.getAnimatedFraction();
                        mLayout.scrollTo(0, -(int) (100 * fa));
                    }
                });

                ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(0, 200);
                valueAnimator2.setDuration(1000);
                valueAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
                //监听动画每一帧的变化
                valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fa = animation.getAnimatedFraction();
                        mLayout.scrollBy(0, (int) (100 * fa));
                    }
                });
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(valueAnimator).before(valueAnimator2);
                animatorSet.start();


            }
        });
    }
}
