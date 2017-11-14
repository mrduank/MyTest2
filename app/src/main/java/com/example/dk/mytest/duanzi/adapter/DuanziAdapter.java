package com.example.dk.mytest.duanzi.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dk.mytest.R;
import com.example.dk.mytest.duanzi.bean.DuanziBean;
import com.example.dk.mytest.utils.LogUtil;
import com.example.dk.mytest.view.CircleImageView;
import com.example.dk.mytest.view.ExpandableTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by dk on 2017/8/29.
 */

public class DuanziAdapter extends RecyclerView.Adapter<DuanziAdapter.DuanziViewHolder> {
    private List<DuanziBean> mDuanziBeen;
    private Fragment fragment;

    public DuanziAdapter(Fragment fragment, List<DuanziBean> mDuanziBeen) {
        this.fragment = fragment;
        this.mDuanziBeen = mDuanziBeen;
    }

    @Override
    public DuanziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.duanzi_item,null);
        return new DuanziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DuanziViewHolder holder, int position) {
        try {
            final DuanziBean duanziBean = mDuanziBeen.get(position);
            LogUtil.d("onBindViewHolder----duanziBeen----"+String.valueOf(duanziBean));

            int commentNum= duanziBean.getGroupBean().getComment();
            int like= duanziBean.getGroupBean().getComment();
            Log.e("commentNum---", String.valueOf(commentNum));
            Log.e("like---", String.valueOf(like));

            String date=getDateToString(duanziBean.getGroupBean().getCreate_time());
            Log.e("date---", String.valueOf(date));
            Log.e("date1---", String.valueOf(getDateToString(1494033932)));
            Glide.with(fragment).load(duanziBean.getGroupBean().getUser().getAvatar_url()).into(holder.mCivAvatar);
            holder.mUserName.setText(duanziBean.getGroupBean().getUser().getName());
            holder.date.setText(date);
//            holder.exptextView.setText(duanziBean.getGroupBean().getText(),position);
            holder.expandableTextView.setText(duanziBean.getGroupBean().getText(),duanziBean.getGroupBean().isCollapsed());
            holder.expandableTextView.setListener(new ExpandableTextView.OnExpandStateChangeListener() {
                @Override
                public void onExpandStateChanged(boolean isExpanded) {
                    duanziBean.getGroupBean().setCollapsed(isExpanded);

                }
            });
//            holder.mTvContent.setText(duanziBean.getGroupBean().getText());
            if (commentNum==0){
                holder.pinglun.setText("评论");
            }else {
                holder.pinglun.setText(String.valueOf(commentNum));
            }
            if (like==0){
                holder.zan.setText("赞");
            }else {
                holder.zan.setText(String.valueOf(like));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String getDateToString(long time) {
        Date d = new Date(time);
       SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    @Override
    public int getItemCount() {
        return mDuanziBeen.size();
    }

    public  static class DuanziViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mCivAvatar;
        private TextView mUserName;
        private TextView date;
//        private TextView mTvContent;
        private RadioButton pinglun,zan;
        private ExpandableTextView expandableTextView;
        private ExpandableTextView exptextView;
        public DuanziViewHolder(View itemView) {
            super(itemView);
            mCivAvatar= (CircleImageView) itemView.findViewById(R.id.msg_icon);
            mUserName= (TextView) itemView.findViewById(R.id.msg_item_name);
            date= (TextView) itemView.findViewById(R.id.msg_item_time);
//            mTvContent= (TextView) itemView.findViewById(R.id.msg_item_content_text);
            pinglun= (RadioButton) itemView.findViewById(R.id.comment_msg);
            zan= (RadioButton) itemView.findViewById(R.id.agree_msg);
            expandableTextView= (ExpandableTextView) itemView.findViewById(R.id.expanded_tv);
//            exptextView= (ExpandableTextView) itemView.findViewById(R.id.expanded_tv);
        }
    }
}
