package com.example.dk.mytest.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.view.TitleView;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class SwipeCardActivity extends BaseActivity implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener {
    private TitleView titleView;
    private SwipeFlingAdapterView swipeView;
    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);
        initData();
        initView();
    }

    private void initData() {
        al = new ArrayList<>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");
        al.add("html");
        al.add("c++");
        al.add("css");
        al.add("javascript");
    }

    private void initView() {
        titleView= (TitleView) findViewById(R.id.swipe_card_title);
        titleView.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swipeView= (SwipeFlingAdapterView) findViewById(R.id.swipe_card_view);
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.swipe_cards_item,R.id.helloText,al);
        swipeView.setAdapter(arrayAdapter);
        swipeView.setFlingListener(this);
        swipeView.setOnItemClickListener(this);
    }

    @Override
    public void removeFirstObjectInAdapter() {
        Log.d("LIST", "removed object!");
        al.remove(0);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLeftCardExit(Object o) {
        Toast.makeText(this,  "Left!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightCardExit(Object o) {
        Toast.makeText(this,  "Right!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAdapterAboutToEmpty(int i) {
        al.add("XML ".concat(String.valueOf(i)));
        arrayAdapter.notifyDataSetChanged();
        Log.d("LIST", "notified");
        i++;
    }

    @Override
    public void onScroll(float scrollProgressPercent) {
        View view = swipeView.getSelectedView();
        view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
        view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
    }

    @Override
    public void onItemClicked(int itemPosition, Object dataObject) {
        Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
    }
}
