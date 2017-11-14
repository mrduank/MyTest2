package com.example.dk.mytest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private TextView textView;
    // 统一消息处理


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        textView = (TextView) findViewById(R.id.text_test);
        textView.setText(getString(R.string.equipment, 10));
    }

    public void toLogin(View view) {
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }).start();


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dissmissProgressDialog();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
        }
    };

    public void clickToRecycleView1(View view) {
        Intent intent = new Intent(this, RecycleActivity.class);
        startActivity(intent);
    }

    public void clickToRecycleView2(View view) {
        Intent intent = new Intent(this, WaterfallActivity.class);
        startActivity(intent);
    }

    public void clickToPhoto(View view) {
        Intent intent = new Intent(this, SampleActivity.class);
        startActivity(intent);
    }

    public void clickToInternetPhoto(View view) {
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
    }


    public void clickToTestSwipeCards(View view) {
        Intent intent = new Intent(this, TestSwipeCardsActivity.class);
        startActivity(intent);
    }

    public void clickToDrawLayoutActivity(View view) {
        Intent intent = new Intent(this, DrawLayoutActivity.class);
        startActivity(intent);
    }

    public static void start(WelcomeActivity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    public void WeixinShare(View view) {
        Intent intent=new Intent(this,WeChatAboutActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
