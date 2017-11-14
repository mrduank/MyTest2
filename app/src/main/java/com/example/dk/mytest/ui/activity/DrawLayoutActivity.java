package com.example.dk.mytest.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.MyMenuAdapter;
import com.example.dk.mytest.bean.MenuItems;
import com.example.dk.mytest.ui.fragment.FirstFragment;
import com.example.dk.mytest.ui.fragment.FourthFragment;
import com.example.dk.mytest.ui.fragment.SecondFragment;
import com.example.dk.mytest.ui.fragment.ThirdFragment;
import com.example.dk.mytest.utils.LogUtil;

import java.util.ArrayList;

public class DrawLayoutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ListView lvLeftMenu;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle;
    private MyMenuAdapter mAdapter;
    private ArrayList<MenuItems> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_layout);
        initDatas();
        initView();
        //设置菜单列表
        mAdapter=new MyMenuAdapter(this,menuItems);
        lvLeftMenu.setAdapter(mAdapter);
        lvLeftMenu.setOnItemClickListener(new DrawerItemClickListener());
        if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    private void initDatas() {
        menuItems=new ArrayList<>();
        menuItems.add(0,new MenuItems(R.drawable.ic_explore_white_24dp,"新鲜事"));
        menuItems.add(1,new MenuItems(R.drawable.ic_mood_white_24dp,"无聊图"));
        menuItems.add(2,new MenuItems(R.drawable.ic_local_florist_white_24dp,"妹子图"));
        menuItems.add(3,new MenuItems(R.drawable.ic_movie_white_24dp,"小电影"));
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu= (ListView) findViewById(R.id.lv_left_menu);
        toolbar.setTitle(mTitle);//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    //下述函数是来捕获ToolBar中的item点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //还可以继续添加其余item的点击事件
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LogUtil.e("onItemClick------", String.valueOf(position));
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        switch (position){
            case 0:
                replaceFragment(new  FirstFragment(),position);
                break;
            case 1:
                replaceFragment(new SecondFragment(),position);
                break;
            case 2:
                replaceFragment(new ThirdFragment(),position);
                break;
            case 3:
                replaceFragment(new FourthFragment(),position);
                break;
        }

    }
    public void replaceFragment(Fragment fragment,int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
        lvLeftMenu.setItemChecked(position,true);
        mDrawerLayout.closeDrawer(lvLeftMenu);
        setTitle(menuItems.get(position).getTitle());
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle= (String) title;
        super.setTitle(title);
    }
}
