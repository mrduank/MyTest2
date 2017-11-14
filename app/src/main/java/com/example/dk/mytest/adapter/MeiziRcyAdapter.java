package com.example.dk.mytest.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dk.mytest.R;
import com.example.dk.mytest.base.Contsts;
import com.example.dk.mytest.bean.MeiziBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dk on 2017/8/28.
 */

public class MeiziRcyAdapter extends RecyclerView.Adapter<MeiziRcyAdapter.MeiziViewHolder>{
    private List<MeiziBean> mMeiziBeanList;
    private Fragment mFragment;

    public MeiziRcyAdapter(List<MeiziBean> mMeiziBeanList, Fragment mFragment) {
        this.mMeiziBeanList = mMeiziBeanList;
        this.mFragment = mFragment;
    }

    @Override
    public MeiziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizi, null);
        return new MeiziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeiziViewHolder holder, final int position) {
//        holder.mIvMeizi.setTag(R.id.mIvMeizi,position);
//        Glide.with(mFragment)
//                .load(mMeiziBeanList.get(position).getImageUrl())
//                .fitCenter()
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.mIvMeizi);
        Glide.with(mFragment)
                .load(mMeiziBeanList.get(position).getImageUrl())
//                .animate(R.anim.item_alpha_in)
                .dontAnimate()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mIvMeizi);
        holder.mIvMeizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> resultList = new ArrayList<String>();
                for (MeiziBean meiziBean : mMeiziBeanList) {
                    resultList.add(meiziBean.getImageUrl());
                }
                Contsts.startImageBrowseActivity(mFragment.getActivity(),resultList,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(mMeiziBeanList.size() > 0){
            return mMeiziBeanList.size();
        }
        return 0;
    }

    public static class MeiziViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvMeizi;
//        ScaleImageView mIvMeizi;
        public MeiziViewHolder(View itemView) {
            super(itemView);
            mIvMeizi = (ImageView) itemView.findViewById(R.id.item_iv_meizi);
//            mIvMeizi = (ScaleImageView) itemView.findViewById(R.id.item_iv_meizi);
        }
    }
}
