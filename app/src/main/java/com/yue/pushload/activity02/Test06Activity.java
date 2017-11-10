package com.yue.pushload.activity02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.yue.pushload.R;
import com.yue.pushload.Test06Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:Test06Activity
 * @auther:shimy
 * @date:2017/11/10 0010 下午 1:26
 * @description: 刷新 加载 参考资料 ：PullToRefreshLayout和smartRefreshLayout
 */
public class Test06Activity extends AppCompatActivity {

    private ListView mListView;
    private Test06Adapter mAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test06);
        initView();
    }

    private void initView() {
        mListView = findViewById(R.id.listview_test06);
        list = new ArrayList<>();
        mAdapter = new Test06Adapter(list);
        mListView.setAdapter(mAdapter);
        refreshData();
    }


    /**
     * 模拟刷新数据
     */
    private void refreshData() {
        list.clear();
        for (int i = 0; i < 10; i++) {
            list.add("刷新哈哈哈");
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 模拟加载数据
     */
    private void loadData() {
        for (int i = 0; i < 10; i++) {
            list.add("加载哈哈哈");
        }
        mAdapter.notifyDataSetChanged();
    }
}
