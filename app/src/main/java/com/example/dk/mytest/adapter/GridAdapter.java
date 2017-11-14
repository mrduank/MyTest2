package com.example.dk.mytest.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.example.dk.mytest.R;
import com.example.dk.mytest.base.Contsts;
import com.example.dk.mytest.bean.Meizi;
import com.example.dk.mytest.view.ScaleImageView;
import com.tsy.sdk.myokhttp.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dk on 2017/7/4.
 */

public class GridAdapter extends BaseAdapter {
    Activity mContext;
    private List<Meizi> datas;//数据
    private ArrayList<String> images =new ArrayList<String>();
    private ArrayList<String> mImageList= new ArrayList<String>();

    public GridAdapter(List<Meizi> datas, Activity mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.image_item_grid, null);
            holder = new ViewHolder();
            holder.imageView = (ScaleImageView) convertView .findViewById(R.id.img_view);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setTag(position);
//        ImageLoader.getInstance().displayImage(datas.get(position).getUrl(),holder.imageView);
        Glide.with(mContext)
                .load(datas.get(position).getUrl())
                .placeholder(R.drawable.jmui_picture_not_found)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p= (int) v.getTag();
                LogUtils.e("gridAdapter========onClick", String.valueOf(p));
                setAeeaylist(p);
            }
        });
        return convertView;
    }
    private void setAeeaylist(int p) {
        mImageList.clear();
        if (datas==null)return;
        String image=null;
        for (int i = 0; i < datas.size(); i++) {
                image=datas.get(i).getUrl();
                mImageList.add(image);
        }
        images.clear();
        for (String s:mImageList) {
            images.add(s);
        }
        Contsts.startImageBrowseActivity(mContext,images,p);
    }
    private class ViewHolder {
        ScaleImageView imageView;
    }
}
