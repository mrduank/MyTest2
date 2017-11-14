package com.example.dk.mytest.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.example.dk.mytest.R;
import com.example.dk.mytest.base.Contsts;
import com.example.dk.mytest.utils.ImageLoaderUtils;
import com.example.dk.mytest.view.ScaleImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

/**
 * Created by dk on 2017/7/3.
 */

public class ImageGridAdapter extends BaseAdapter {
    private static final String TAG = "ImageGridAdapter";
    private static final boolean DEBUG = true;
    private ArrayList<String> mImageList;
    private LayoutInflater mLayoutInflater;
    Activity context;
    private DisplayImageOptions initOptions;
    private ArrayList<String> images = new ArrayList<>();
    private ViewHolder holder;


    public ImageGridAdapter(Activity context, ArrayList<String> mImageList) {
        this.context = context;
        this.mImageList = mImageList;
        mLayoutInflater=LayoutInflater.from(context);
        initOptions= ImageLoaderUtils.initOptions();

    }

    @Override
    public int getCount() {
        return mImageList==null?0:mImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (DEBUG)
            Log.i(TAG, "position = " + position);
//        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.image_item, null);
            holder = new ViewHolder();
            holder.imageView = (ScaleImageView) convertView .findViewById(R.id.img_view);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setTag(position);
//        ImageLoader.getInstance().displayImage(mImageList.get(position),holder.imageView,initOptions);
        Glide.with(context).load(mImageList.get(position)).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p= (int) v.getTag();
                setAeeaylist(p);
            }
        });

        return convertView;
    }
    private void setAeeaylist(int p) {
        images.clear();
        for (String s:mImageList) {
            images.add(s);
        }
        Contsts.startImageBrowseActivity(context,images,p);
//        Intent intent = new Intent(context, ImageBrowseActivity.class);
//        intent.putExtra("images", images);//非必须
//        intent.putExtra("position", p);
//        int[] location = new int[2];
//        holder.imageView.getLocationOnScreen(location);
//        intent.putExtra("locationX", location[0]);//必须
//        intent.putExtra("locationY", location[1]);//必须
//        intent.putExtra("width", holder.imageView.getWidth());//必须
//        intent.putExtra("height", holder.imageView.getHeight());//必须
//        context.startActivity(intent);
//        context.overridePendingTransition(0, 0);
    }
    private class ViewHolder {
        ScaleImageView imageView;
    }
}
