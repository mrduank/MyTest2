package com.example.dk.mytest.duanzi.ui;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.dk.mytest.R;
import com.example.dk.mytest.dialog.AnimationDialog;
import com.example.dk.mytest.duanzi.adapter.DuanziAdapter;
import com.example.dk.mytest.duanzi.api.DuanziApi;
import com.example.dk.mytest.duanzi.bean.DuanziBean;
import com.example.dk.mytest.utils.LogUtil;
import com.example.dk.mytest.utils.helper.GsonHelper;
import com.example.dk.mytest.utils.net.VolleyHelper;
import com.example.dk.mytest.utils.net.VolleyResponseCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuanziFragment extends Fragment {
    private List<DuanziBean> duanziBeen=new ArrayList<>();
    private Dialog dialog;

    @Bind(R.id.duanzi_recycle)
    RecyclerView duanziRecycle;
    @Bind(R.id.duanzi_swipe)
    SwipeRefreshLayout duanziSwipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_duanzi, container, false);
        ButterKnife.bind(this, view);
        dialog=new AnimationDialog(getContext());
        dialog.setCanceledOnTouchOutside(false);
        initData();
        initRefresh();
        return view;
    }

    private void initRefresh() {
        duanziSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog.show();
                initData();
                duanziSwipe.setRefreshing(false);
            }
        });
    }

    private void initData() {
        VolleyHelper.sendHttpGet(getContext(), DuanziApi.GET_DUANZI, new VolleyResponseCallback() {
            @Override
            public void onSuccess(String response) {
                dialog.dismiss();
                duanziBeen= GsonHelper.getDuanziBean(response);
                LogUtil.d("initData----duanziBeen----"+String.valueOf(duanziBeen));
                if (duanziBeen!=null&&duanziBeen.size()!=0){
                    duanziBeen.remove(3);
                }
                duanziRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
                duanziRecycle.setAdapter(new DuanziAdapter(DuanziFragment.this,duanziBeen));
            }

            @Override
            public void onError(VolleyError error) {
                Log.d("onError: ", String.valueOf(error));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
