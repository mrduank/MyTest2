package com.example.dk.mytest.base;

import com.bumptech.glide.request.target.ViewTarget;
import com.example.dk.mytest.R;
import com.example.dk.mytest.utils.ImageLoaderUtils;
import com.example.dk.mytest.utils.LogUtil;
import com.mob.MobApplication;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by dk on 2017/7/3.
 */

public class MyApplication extends MobApplication {
    public static IWXAPI mWxApi;

    public void onCreate(){
        ImageLoaderUtils.initConfiguration(getApplicationContext());
        ViewTarget.setTagId(R.id.img_view);
        registToWX();//向微信注册APP
    }
    private void registToWX() {
        LogUtil.d("registToWX------->");
        mWxApi = WXAPIFactory.createWXAPI(this, AppContext.APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(AppContext.APP_ID);
    }
}
