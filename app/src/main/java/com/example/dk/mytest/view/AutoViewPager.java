package com.example.dk.mytest.view;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.example.dk.mytest.adapter.BaseViewPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dk on 2017/8/22.
 */

public class AutoViewPager extends ViewPager {
    private static final String TAG = "AutoViewPager";

    private int currentItem;

    private Timer mTimer;
    private AutoHandler mHandler = new AutoHandler();

    public AutoViewPager(Context context) {
        super(context);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void init(AutoViewPager viewPager,BaseViewPagerAdapter adapter){
        adapter.init(viewPager,adapter);
    }
    public void start(){
        //先停止
        onStop();
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new AutoTask(),3000,3000);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentItem = getCurrentItem();
            if(currentItem == getAdapter().getCount() - 1){
                currentItem = 0 ;
            }else {
                currentItem++ ;
            }
            setCurrentItem(currentItem);
            //更新小圆点状态
            int nextItem = (currentItem % ((BaseViewPagerAdapter)getAdapter()).getRealCount());
            Log.e("run:---nextItem ", String.valueOf(nextItem));
            onPageSelected(nextItem);
        }
    };

    public void onDestroy() {
        onStop();
    }

    private final static class AutoHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
    public void updatePointView(int size){
        if (getParent()instanceof ViewPagerPointView){
            ViewPagerPointView pointView= (ViewPagerPointView) getParent();
            pointView.initPointView(size);
        }else {
            Log.e(TAG,"parent view not be ViewPagerPointView");
        }
    }

    public void onPageSelected(int position) {
        if (getParent() == null) {
            return;
        }

        if (getParent() instanceof ViewPagerPointView) {
            ViewPagerPointView pager = (ViewPagerPointView) getParent();
            pager.updatePointView(position);
        }
    }

    private class AutoTask extends TimerTask {
        @Override
        public void run() {
            mHandler.post(runnable);
        }

    }
    private void onStop() {
        //先取消定时器
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
    public void onResume(){
        start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                onStop();
                break;
            case MotionEvent.ACTION_UP:
                onResume();
                break;
        }
        return super.onTouchEvent(ev);
    }
}
