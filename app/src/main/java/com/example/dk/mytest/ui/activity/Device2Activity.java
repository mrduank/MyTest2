package com.example.dk.mytest.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.SwipeListAdapter;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.view.TitleView;

import java.util.ArrayList;
import java.util.List;

public class Device2Activity extends BaseActivity {
    private ListView listView;
    private List<String> list=new ArrayList<String>();
    private SwipeListAdapter adapter;
    private TitleView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device2);
        getData();
        initView();


    }

    private void initView() {
        title= (TitleView) findViewById(R.id.swipe_title);
        title.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView= (ListView) findViewById(R.id.listview);
        adapter=new SwipeListAdapter(this,list);
        listView.setAdapter(adapter);
        adapter.setOnClickListenerEditOrDelete(new SwipeListAdapter.OnClickListenerEditOrDelete() {
            @Override
            public void OnClickListenerEdit(int position) {
                Toast.makeText(Device2Activity.this, "edit....", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnClickListenerDelete(int position) {
                Toast.makeText(Device2Activity.this, "delete...", Toast.LENGTH_SHORT).show();
                adapter.delete(position);

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
