package com.example.dk.mytest.utils;

/**
 * 获取随机数的工具类
 * Created by dk on 2017/8/28.
 */

public class GetRandom {
    /**
     * 获取一个 0 到 48 之间的随机整数
     *
     * @return 一个 随机整数
     */
    public static int getRandom(){
        double random = Math.random();
        int result = (int)(random * 50 - 2);
        return Math.abs(result);
    }


//
//    /**
//     * 获取一个 10 到 20 之间的随机整数
//     *
//     * @return
//     */
//    public static int getRandom20(){
//        double random = Math.random();
//        int result = (int)(random * 10 + 10);
//        return result;
//    }
}
