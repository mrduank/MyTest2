package com.example.dk.mytest.utils.helper;

import com.example.dk.mytest.bean.Meizi;
import com.example.dk.mytest.bean.MeiziBean;
import com.example.dk.mytest.duanzi.bean.DuanziBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Gson 的处理类
 *
 * Created by dk on 2017/8/28.
 */

public class GsonHelper {
    /**
     * 将一个 String 类型的数据解析成一个 List<MeiziBean>
     *
     * @param response 包含妹子信息的 String
     * @return List<MeiziBean>
     */

    public static List<MeiziBean> getMeiziBean(String response){
        List<MeiziBean> meiziBeen=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(response);
            String meiziArray=jsonObject.optString("results");
            Gson gson=new Gson();
            meiziBeen=gson.fromJson(meiziArray,new TypeToken<List<MeiziBean>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return meiziBeen;
    }


    public static List<Meizi> getMeizi(String response){
        List<Meizi> meizi=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(response);
            String meiziArray=jsonObject.optString("results");
            Gson gson=new Gson();
            meizi=gson.fromJson(meiziArray,new TypeToken<List<Meizi>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return meizi;
    }


    public static List<DuanziBean> getDuanziBean(String response) {
        List<DuanziBean> duanzi=new ArrayList<>();
        try {
            JSONObject json=new JSONObject(response);
            String duanziArry=json.getJSONObject("data").optString("data");
            Gson gson=new Gson();
            duanzi=gson.fromJson(duanziArry,new TypeToken<List<DuanziBean>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return duanzi;
    }
}
