package com.example.dk.mytest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.customprogress.CustomProgress;

public class TestSwipeCardsActivity extends BaseActivity {
    private CustomProgress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_swip_cards);
        initView();
    }

    private void initView() {
        progress= (CustomProgress) findViewById(R.id.custom_progress);
        progress.setmTotalProgress(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(50);
                        Message msg=handler.obtainMessage();
                        msg.what=1;
                        msg.arg1=i;
                        handler.sendMessage(msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if (msg.what==1){
               progress.setProgress(msg.arg1);
           }
        }
    };


    public void clickToSwipeCards(View view) {
        Intent intent=new Intent(this,SwipeCardActivity.class);
        startActivity(intent);
    }

    public void clickToAnimation(View view) {
        Intent intent=new Intent(this,AnimationCardsActivity.class);
        startActivity(intent);
    }
}
