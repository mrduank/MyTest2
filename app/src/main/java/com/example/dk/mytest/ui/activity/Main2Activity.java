package com.example.dk.mytest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.sweetdialog.SweetDialogActivity;

public class Main2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
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



    public void WeixinShare(View view) {
        Intent intent=new Intent(this,WeChatAboutActivity.class);
        startActivity(intent);
    }

    public void toDialog(View view) {
        Intent intent=new Intent(this,SweetDialogActivity.class);
        startActivity(intent);
    }
}
