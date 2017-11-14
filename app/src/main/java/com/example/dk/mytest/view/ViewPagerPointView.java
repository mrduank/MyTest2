package com.example.dk.mytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.BaseViewPagerAdapter;

/**
 * Created by dk on 2017/8/22.
 */

public class ViewPagerPointView extends RelativeLayout {
    private AutoViewPager mViewPager;

    private Context mContext;

    private LinearLayout layout;

    public ViewPagerPointView(Context context) {
        super(context);
        init(context);
    }

    public ViewPagerPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mViewPager = new AutoViewPager(context);
        layout = new LinearLayout(mContext);
        addView(mViewPager);
    }
    public void setAdapter(BaseViewPagerAdapter adapter) {
        if (mViewPager != null) {
            mViewPager.init(mViewPager, adapter);
        }
    }

    public AutoViewPager getViewPager() {
        return mViewPager;
    }


    public void initPointView(int size) {
        layout=new LinearLayout(mContext);
        for (int i = 0; i < size; i++) {
            ImageView image=new ImageView(mContext);
            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(20,20);
            param.leftMargin=8;
            param.gravity= Gravity.CENTER;
            image.setLayoutParams(param);
            if (i==0){
                image.setBackgroundResource(R.drawable.point_checked);
            }else {
                image.setBackgroundResource(R.drawable.point_normal);
            }
            layout.addView(image);
        }
        LayoutParams layoutParam=new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.addRule(ALIGN_PARENT_BOTTOM);
        layoutParam.addRule(CENTER_HORIZONTAL);
        layoutParam.setMargins(12, 20, 12, 20);
        layout.setLayoutParams(layoutParam);
        addView(layout);
    }
    public void updatePointView(int position) {
        int size = layout.getChildCount();
        for (int i = 0; i < size; i++) {
            ImageView imageView = (ImageView) layout.getChildAt(i);
            if (i == position) {
                imageView.setBackgroundResource(R.drawable.point_checked);
            } else {
                imageView.setBackgroundResource(R.drawable.point_normal);
            }

        }
    }
    public void onDestroy() {
        if (mViewPager != null) {
            mViewPager.onDestroy();
        }
    }

}
