package com.example.dk.mytest.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.ViewPagerAdapter;
import com.example.dk.mytest.utils.ViewPagerFixed;

import java.util.List;

public class ImageBrowseActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPagerFixed mViewPager;
    private TextView hint;
    private List<String> images;
    private int position;
//    public static int TYPE;
    private ViewPagerAdapter adapter;
//    private int mLocationX;
//    private int mLocationY;
//    private int mWidth;
//    private int mHeight;
//    private SmoothImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browser);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPagerFixed) findViewById(R.id.viewPager);
        hint = (TextView) findViewById(R.id.hint);
//        View main=View.inflate(this,R.layout.activity_image_browser,null);
//        main.getParent().requestDisallowInterceptTouchEvent(true);

        Intent intent = getIntent();
        images = intent.getStringArrayListExtra("images");
        Log.e("cs--------Browseimages", String.valueOf(images.size()));
        position = intent.getIntExtra("position", 0);
//        mLocationX = getIntent().getIntExtra("locationX", 0);
//        mLocationY = getIntent().getIntExtra("locationY", 0);
//        mWidth = getIntent().getIntExtra("width", 0);
//        mHeight = getIntent().getIntExtra("height", 0);
//        imageView = new SmoothImageView(this);
//        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
//        imageView.transformIn();
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        setContentView(imageView);
//        ImageLoader.getInstance().displayImage(images.get(position), imageView);

//        TYPE = intent.getIntExtra("type", 0);
        //设置ViewPager
        setImageBrowse(images, position);
    }

    private void setImageBrowse(List<String> images, int position) {
        if (adapter == null && images != null && images.size() != 0) {
            adapter = new ViewPagerAdapter(this, images);
            mViewPager.setAdapter(adapter);
            mViewPager.setCurrentItem(position);
            mViewPager.addOnPageChangeListener(this);
            hint.setText(position + 1 + "/" + images.size());
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        hint.setText(position+1+"/"+images.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
