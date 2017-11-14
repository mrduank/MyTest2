package com.example.dk.mytest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.MyRecycleAdapter;
import com.example.dk.mytest.helper.OnRecyclerItemClickListener;
import com.example.dk.mytest.helper.SimpleItemTouchCallback;
import com.example.dk.mytest.utils.MyDividerItemDecoration;
import com.example.dk.mytest.utils.VibratorUtil;
import com.tsy.sdk.myokhttp.util.LogUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment implements View.OnClickListener {
    private ArrayList<String> datas=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyRecycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView add,delete;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_second, container, false);
        Log.e("SecondFragment", "onCreateView");
        initData();
        initView(view);
        return view;
    }

//    @Override
//    public void onResume() {
//        Log.e("SecondFragment", "onResume");
//        initData();
//        if (mAdapter == null) return;
//        mAdapter.notifyDataSetChanged();
//        super.onResume();
//    }

    private void initData() {
        String temp=" item";
        for (int i = 0; i < 10; i++) {
            datas.add(i+temp);
        }
        Log.e("initData", datas.toString());
    }


    private void initView(View view) {
        add= (TextView)view.findViewById(R.id.add_item);
        delete= (TextView) view.findViewById(R.id.del_item);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recycle_view);
        mAdapter = new MyRecycleAdapter(datas);
        mLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        MyDividerItemDecoration item=new MyDividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL);
        item.setDivider(R.drawable.divider_shape_bg);
        mRecyclerView.addItemDecoration(item);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        final ItemTouchHelper helper=new ItemTouchHelper(new SimpleItemTouchCallback(mAdapter));
        LogUtils.e("helper==========", String.valueOf(helper));
        helper.attachToRecyclerView(mRecyclerView);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView){
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                //如果item不是最后一个，则执行拖拽
                if (vh.getLayoutPosition()!=datas.size()-1) {
                    helper.startDrag(vh);//纵向拖拽
//                    helper.startSwipe(vh);//侧滑直接删除
                    VibratorUtil.Vibrate(getActivity(), 70);   //震动70ms
                }
            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                String item = datas.get(vh.getLayoutPosition());
                Toast.makeText(getActivity(), ""+item.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_item:
                mAdapter.addNewItem();
                // 由于Adapter内部是直接在首个Item位置做增加操作，增加完毕后列表移动到首个Item位置
                mLayoutManager.scrollToPosition(0);
                break;
            case R.id.del_item:
                mAdapter.deleteItem();
                // 由于Adapter内部是直接在首个Item位置做删除操作，删除完毕后列表移动到首个Item位置
                mLayoutManager.scrollToPosition(0);
                break;
        }

    }

}
