package com.example.dk.mytest.ui.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.GridAdapter;
import com.example.dk.mytest.bean.Meizi;
import com.example.dk.mytest.lib.MultiColumnListView;
import com.example.dk.mytest.utils.MyOkhttp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tsy.sdk.myokhttp.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    private MultiColumnListView mAdapterView;
    private ArrayList<Meizi> meizis;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_third, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mAdapterView = (MultiColumnListView)view.findViewById(R.id.list);
        //执行加载数据
        new GetData().execute("http://gank.io/api/data/福利/10/1");
    }
    private class GetData extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return   MyOkhttp.get(params[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!TextUtils.isEmpty(result)){
                JSONObject jsonObject;
                Gson gson=new Gson();
                String jsonData=null;
                LogUtils.e("onPostExecute------------");
                try {
                    jsonObject = new JSONObject(result);
                    jsonData = jsonObject.getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(meizis==null||meizis.size()==0){
                    meizis= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
                    Log.e( "onPostExecute:---1 ", String.valueOf(meizis));
                }else{
                    List<Meizi>  more= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
                    meizis.addAll(more);
                    Log.e( "onPostExecute:---2 ", String.valueOf(meizis));
                }
                mAdapterView.setAdapter(new GridAdapter(meizis,getActivity()));
            }
        }
    }

}
