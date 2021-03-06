package com.example.dk.mytest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.view.SwipeMenuLayout;

import java.util.List;

/**
 * Created by dk on 2017/6/30.
 */

public class SwipeListAdapter extends BaseAdapter {
    private List<String> groupNames;
    private Context context;
    private OnClickListenerEditOrDelete onClickListenerEditOrDelete;

    public SwipeListAdapter(Context context, List<String> groupNames) {
        this.context = context;
        this.groupNames = groupNames;
    }

    @Override
    public int getCount() {
        return groupNames.size();
    }

    @Override
    public Object getItem(int position) {
        return groupNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String name=groupNames.get(position);
        Log.e("getView: ------", String.valueOf(groupNames.size()));
        Log.e("getView: ------", name);
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.swipe_item_lay,null);
            holder.tvName= (TextView) convertView.findViewById(R.id.device_group_tv);
            holder.tvEdit= (TextView) convertView.findViewById(R.id.edit_tv);
            holder.tvDelete= (TextView) convertView.findViewById(R.id.del_tv);
            holder.group= (RelativeLayout) convertView.findViewById(R.id.device_group_item);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(name);
        final View swipeView=convertView;

        holder.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "dianji", Toast.LENGTH_SHORT).show();

            }
        });

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListenerEditOrDelete!=null){
                    onClickListenerEditOrDelete.OnClickListenerEdit(position);
                }
            }
        });

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "delete....", Toast.LENGTH_SHORT).show();
                if (onClickListenerEditOrDelete!=null){
                    onClickListenerEditOrDelete.OnClickListenerDelete(position);
                    ((SwipeMenuLayout)swipeView).quickClose();
                }
            }
        });
        return convertView;
    }

    public void delete(int position) {
        groupNames.remove(position);
        notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView tvName,tvEdit,tvDelete;
        RelativeLayout group;
    }
    public interface OnClickListenerEditOrDelete{
        void OnClickListenerEdit(int position);
        void OnClickListenerDelete(int position);
    }

    public void setOnClickListenerEditOrDelete(OnClickListenerEditOrDelete onClickListenerEditOrDelete1){
        this.onClickListenerEditOrDelete=onClickListenerEditOrDelete1;
    }

}
