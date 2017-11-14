package com.example.dk.mytest.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dk.mytest.view.AutoViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dk on 2017/8/22.
 */

public class BaseViewPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private Context ctx;
    private List<ImageView> views = new ArrayList<ImageView>();
    private AutoViewPager mView;

    private static final int ANIMATION_DURATION = 2000;
    private static final float SCALE_END = 1.13F;

    public BaseViewPagerAdapter(Context ctx, List<ImageView> views) {
        this.ctx = ctx;
        this.views = views;
    }

    @Override
    public int getCount() {
        return views == null ? 0 : Integer.MAX_VALUE;
    }

    public int getRealCount() {
        return views == null ? 0 : views.size();
    }
    public void init(AutoViewPager viewPager,BaseViewPagerAdapter adapter){
        mView = viewPager;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);

        if (views == null || views.size() == 0){
            return;
        }
        //设置初始为中间，这样一开始就能够往左滑动了
        int position = Integer.MAX_VALUE/2 - (Integer.MAX_VALUE/2) % getRealCount();
        Log.e("init-----position", String.valueOf(position));
        mView.setCurrentItem(position);
        mView.start();
        mView.updatePointView(getRealCount());
    }
    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mView, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mView, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.e("instantiateItem-----1", String.valueOf(position % getRealCount()));
        View v = views.get(position % getRealCount());
        container.removeView(v);
        container.addView(v);
        animateImage();
        Log.e("instantiateItem-----2", String.valueOf(v));
        return v;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mView.onPageSelected(position % getRealCount());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
