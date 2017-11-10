package com.yue.pushload.pushload;

import android.view.View;

/**
 * @ClassName:RefreshHeader
 * @auther:shimy
 * @date:2017/11/9 0009 上午 9:38
 * @description: 自定义头部需要实现此接口
 */

public interface RefreshHeader {
    /**
     * 其实不需要继承此方法的，但当继承viewgroup自定义时就需要这个方法，因为要addView()
     *
     * @return
     */
    View getView();

    /**
     * @param oldState 上一个状态
     * @param newState 新的状态
     */
    void onStateChanged(RefreshState oldState, RefreshState newState);
}
