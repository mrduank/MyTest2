package com.example.dk.mytest.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.CommonAdapter;
import com.example.dk.mytest.base.BaseActivity;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback;
import com.tsy.sdk.myokhttp.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class AnimationCardsActivity extends BaseActivity {
    private RecyclerView mRv;
    private List<ImageView> mDatas;
    private CommonAdapter mAdapter;
    public static final int[] IMAGES = new int[]{
            R.drawable.chrysanthemum,
            R.drawable.desert,
            R.drawable.hydrangeas,
            R.drawable.jellyfish,
            R.drawable.koala,
            R.drawable.lighthouse,
            R.drawable.penguins,
            R.drawable.tulips
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_cards);
        initDatas();
        initView();
    }

    private void initDatas() {
        mDatas=new ArrayList<>();
        for (int i = 0; i < IMAGES.length; i++) {
            ImageView img=new ImageView(this);
            img.setImageResource(IMAGES[i]);
            mDatas.add(img);
        }
        LogUtils.e("initDatas======1", String.valueOf(mDatas));
    }

    private void initView() {
        mRv= (RecyclerView) findViewById(R.id.recycle_view);
        mAdapter=new CommonAdapter(mDatas);
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(new OverLayCardLayoutManager());
        CardConfig.initConfig(this);
        ItemTouchHelper.Callback callback=new RenRenCallback(mRv,mAdapter,mDatas);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRv);

    }

}
