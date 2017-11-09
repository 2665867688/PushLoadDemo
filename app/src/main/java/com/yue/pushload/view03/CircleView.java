package com.yue.pushload.view03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yue.pushload.R;

/**
 * @ClassName:CircleView
 * @auther:shimy
 * @date:2017/11/8 0008 下午 12:18
 * @description: 自定义一个圆 继承view 不涉及布局
 */
public class CircleView extends View {

    private int mWidth;
    private int mHeight;
    private Paint paint;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            /*
             * 处理 wrap_parent的情况 不处理 wrap_parent会跟match_parent一样的效果，
             * 下面指定一种默认值
             */
            setMeasuredDimension(200, 200);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 处理padding属性
         */
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        mWidth = getWidth() - paddingLeft - paddingRight;
        mHeight = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(mWidth, mHeight) / 2;
        canvas.drawCircle(paddingLeft + mWidth / 2, paddingTop + mHeight / 2, radius, paint);
    }
}
