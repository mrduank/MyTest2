package com.example.dk.mytest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dk.mytest.R;
import com.example.dk.mytest.helper.SimpleItemTouchCallback;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dk on 2017/6/30.
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHolder> implements SimpleItemTouchCallback.OnItemTouchCallbackListener {
    //    private Context mContext;
//    private List<Meizi> datas;//数据
    private ArrayList<String> mData;

    public MyRecycleAdapter(ArrayList<String> mData) {
        this.mData = mData;
    }

    public void upData(ArrayList<String> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void addNewItem() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(0, "new Item");
        notifyItemInserted(0);
    }

    public void deleteItem() {
        if (mData == null || mData.isEmpty()) {
            return;
        }
        if (mData.size() > 1) {
            mData.remove(0);
            notifyItemRemoved(0);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_recycle_layout, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? null : mData.size();
    }

    @Override
    public void onSwiped(int adapterPosition) {
        if (mData == null) {
            return;
        }
        if (mData.size() > 1) {
            mData.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
        }
    }

    @Override
    public void onMove(int fromPosition, int targetPosition) {
        if (mData!=null) {
            if (fromPosition < targetPosition) {
                for (int i = fromPosition; i < targetPosition; i++) {
                    Collections.swap(mData, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > targetPosition; i--) {
                    Collections.swap(mData, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, targetPosition);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.rcy_item_tv);
        }
    }

}
