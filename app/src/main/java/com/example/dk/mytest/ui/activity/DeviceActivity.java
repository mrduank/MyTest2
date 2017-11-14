package com.example.dk.mytest.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.SlideListAdapter;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.view.SlideListView;

import java.util.ArrayList;
import java.util.List;

public class DeviceActivity extends BaseActivity {
    private SlideListView listView;
    private List<String> list=new ArrayList<String>();
    private SlideListAdapter listViewSlideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        getData();
        initView();
    }

    private void initView() {
        listView= (SlideListView) findViewById(R.id.slide_listview);
        listViewSlideAdapter=new SlideListAdapter(this,list);
        listView.setAdapter(listViewSlideAdapter);
        Log.e( "initView:----- ", String.valueOf(listView));
        listViewSlideAdapter.setOnClickListenerEditOrDelete(new SlideListAdapter.OnClickListenerEditOrDelete() {
            @Override
            public void OnClickListenerEdit(int position) {
                Toast.makeText(DeviceActivity.this, "edit....", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnClickListenerDelete(int position) {
                Toast.makeText(DeviceActivity.this, "delete....", Toast.LENGTH_SHORT).show();
                listViewSlideAdapter.delete(position);
                listView.turnToNormal();
            }
        });

    }

    private void getData() {
        for (int i = 0; i < 30; i++) {
            list.add(new String("第"+i+"项"));
        }
        Log.e( "getData:--- ", String.valueOf(list.size()));

    }
}
