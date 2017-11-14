package com.example.dk.mytest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dk.mytest.R;
import com.tsy.sdk.myokhttp.util.LogUtils;

import java.util.List;

/**
 * Created by dk on 2017/8/7.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {
    private List<ImageView> mDatas;
    public CommonAdapter( List<ImageView> mDatas) {
        this.mDatas = mDatas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.e("onCreateViewHolder======");
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avanter,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogUtils.e("onBindViewHolder======", String.valueOf(mDatas.get(position).getDrawable()));
        holder.imageView.setImageDrawable(mDatas.get(position).getDrawable());
//        ImageLoader.getInstance().displayImage(mDatas.get(position).getUrl(),holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
