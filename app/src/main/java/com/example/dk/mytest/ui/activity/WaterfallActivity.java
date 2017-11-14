package com.example.dk.mytest.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.WaterAdapter;
import com.example.dk.mytest.utils.MDGridRvDividerDecoration;

import java.util.ArrayList;

public class WaterfallActivity extends Activity {
    private RecyclerView mRecyclerView;
    private WaterAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfall);
        initData();
        initView();
    }

    private void initData() {
        mLayoutManager=new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        mAdapter = new WaterAdapter(getData());
    }

    private void initView() {
        mRecyclerView= (RecyclerView) findViewById(R.id.waterfall_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        // 设置间隔样式
        mRecyclerView.addItemDecoration(new MDGridRvDividerDecoration(this));
    }

    private ArrayList<String> getData() {
        ArrayList<String> data=new ArrayList<>();
        String temp=" item";
        for (int i = 0; i < 40; i++) {
            data.add(i+temp);
        }
        return data;
    }
}
