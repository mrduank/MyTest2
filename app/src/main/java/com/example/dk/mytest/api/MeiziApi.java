package com.example.dk.mytest.api;

import com.example.dk.mytest.utils.GetRandom;

/**
 * Created by dk on 2017/8/28.
 */

public class MeiziApi {
    public static String getMeiziApi(){
        StringBuilder meiziApi = new StringBuilder();
        meiziApi.append("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/").append("15").append("/" + GetRandom.getRandom());
        return String.valueOf(meiziApi);
    }
}
