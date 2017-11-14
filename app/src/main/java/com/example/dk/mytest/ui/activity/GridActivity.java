package com.example.dk.mytest.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.GridAdapter;
import com.example.dk.mytest.api.MeiziApi;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.bean.Meizi;
import com.example.dk.mytest.lib.MultiColumnListView;
import com.example.dk.mytest.utils.helper.GsonHelper;
import com.example.dk.mytest.utils.net.VolleyHelper;
import com.example.dk.mytest.utils.net.VolleyResponseCallback;
import com.example.dk.mytest.view.TitleView;
import com.tsy.sdk.myokhttp.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends BaseActivity {
    private MultiColumnListView mAdapterView = null;
    private List<Meizi> meizis=new ArrayList<>();
    private TitleView title;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        initView();
    }

    private void initView() {
        title= (TitleView) findViewById(R.id.grid_title);
        title.setTitle(getString(R.string.internet_load));
        title.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapterView = (MultiColumnListView) findViewById(R.id.list);
        refreshLayout= (SwipeRefreshLayout) findViewById(R.id.grid_refresh);
        //执行加载数据
        showProgressDialog();
        initData();
        refreshView();
//        new GetData().execute("http://gank.io/api/data/福利/10/1");
//        new GetData().execute(MeiziApi.getMeiziApi());
    }

    private void refreshView() {
        refreshLayout.setColorSchemeResources(R.color.bg_top);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgressDialog();
                initData();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void initData() {
        VolleyHelper.sendHttpGet(this, MeiziApi.getMeiziApi(), new VolleyResponseCallback() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e("response----------",response);
                meizis= GsonHelper.getMeizi(response);
                mAdapterView.setAdapter(new GridAdapter(meizis,GridActivity.this));
                dissmissProgressDialog();
            }

            @Override
            public void onError(VolleyError error) {
                Log.d("sendHttpGet#onError----","网络请求失败");
            }
        });
    }


//    private class GetData extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            return   MyOkhttp.get(params[0]);
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (!TextUtils.isEmpty(result)){
//                JSONObject jsonObject;
//                Gson gson=new Gson();
//                String jsonData=null;
//                LogUtils.e("onPostExecute------------");
//                try {
//                    jsonObject = new JSONObject(result);
//                    jsonData = jsonObject.getString("results");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if(meizis==null||meizis.size()==0){
//                    meizis= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
//                    Log.e( "onPostExecute:---1 ", String.valueOf(meizis));
//                }else{
//                    List<Meizi>  more= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
//                    meizis.addAll(more);
//                    Log.e( "onPostExecute:---2 ", String.valueOf(meizis));
//                }
//                    mAdapterView.setAdapter(new GridAdapter(meizis,GridActivity.this));
//                dissmissProgressDialog();
//                }
//            }
//        }
    }


