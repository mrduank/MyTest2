package com.example.dk.mytest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dk on 2017/8/29.
 */

public class DataUtils {
    private SimpleDateFormat sf =null;
    //获取系统时间 格式为："yyyy/MM/dd "
    public String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }
    public String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }
    }
