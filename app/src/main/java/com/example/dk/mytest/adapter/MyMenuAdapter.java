package com.example.dk.mytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dk.mytest.R;
import com.example.dk.mytest.bean.MenuItems;

import java.util.ArrayList;

/**
 * Created by dk on 2017/8/16.
 */

public class MyMenuAdapter extends BaseAdapter {
    private ArrayList<MenuItems> menuItems;
    private Context context;

    public MyMenuAdapter(Context context, ArrayList<MenuItems> menuItems) {
        this.context = context;
        this.menuItems = menuItems;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.drawer_item,null);
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            holder.img_menu= (ImageView) convertView.findViewById(R.id.img_menu);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(menuItems.get(position).getTitle());
        holder.img_menu.setImageResource(menuItems.get(position).getResourceId());
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_title;
        private ImageView img_menu;
    }
}
