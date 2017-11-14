package com.example.dk.mytest.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.view.TitleView;

public class MoodActivity extends BaseActivity {
    private TitleView titleView;
    private EditText editText;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        initView();
    }

    private void initView() {
        titleView= (TitleView) findViewById(R.id.mood_title);
        editText= (EditText) findViewById(R.id.add_content_send);
        gridView= (GridView) findViewById(R.id.send_add);

        titleView.setTitle(R.string.send_title_text);
        titleView.setTitleleftText(R.string.cancel);
        titleView.setTitleRightText(R.string.send_text);
        titleView.setRightTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MoodActivity.this, "发送成功.......", Toast.LENGTH_SHORT).show();
            }
        });
        titleView.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
