package com.example.dk.mytest.ui.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.MeiziRcyAdapter;
import com.example.dk.mytest.api.MeiziApi;
import com.example.dk.mytest.bean.MeiziBean;
import com.example.dk.mytest.dialog.ProgressDialog;
import com.example.dk.mytest.utils.helper.GsonHelper;
import com.example.dk.mytest.utils.net.VolleyHelper;
import com.example.dk.mytest.utils.net.VolleyResponseCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    List<MeiziBean> meiziBeanList = new ArrayList<>();
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_meizi, container, false);
        initData();
        initView(view);
        refreshMeizi();
        return view;
    }

    private void initView(View view) {
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.meizi_refresh);
        recyclerView= (RecyclerView) view.findViewById(R.id.meizi_rv_show_meizi);
        dialog=new ProgressDialog(getContext());
        dialog.setCanceledOnTouchOutside(false);
    }

    //刷新当前界面
    private void refreshMeizi() {
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog.show();
                initData();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void initData() {
        VolleyHelper.sendHttpGet(getActivity(), MeiziApi.getMeiziApi(), new VolleyResponseCallback() {
            @Override
            public void onSuccess(String response) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                meiziBeanList= GsonHelper.getMeiziBean(response);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                Collections.shuffle(meiziBeanList);
                recyclerView.setAdapter(new MeiziRcyAdapter(meiziBeanList,MeiziFragment.this));
            }

            @Override
            public void onError(VolleyError error) {
                Log.e("MeiziFrag--onError--","网络请求失败");
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
