package com.example.dk.mytest.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.AppContext;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.base.MyApplication;
import com.example.dk.mytest.utils.ShareSDKUtils;
import com.mob.MobSDK;
import com.mob.tools.utils.UIHandler;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class WeChatAboutActivity extends BaseActivity implements View.OnClickListener,PlatformActionListener, Handler.Callback  {
    private static final int MSG_USERID_FOUND = 1; // 用户信息已存在
    private static final int MSG_LOGIN = 2; // 登录操作
    private static final int MSG_AUTH_CANCEL = 3; // 授权取消
    private static final int MSG_AUTH_ERROR = 4; // 授权错误
    private static final int MSG_AUTH_COMPLETE = 5; // 授权完成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_about);
        MobSDK.init(this, AppContext.APP_ID,AppContext.APP_SECRET);//初始化SDk
        findViewById(R.id.showShare).setOnClickListener(this);
    }

    public void WeChatShare(View view) {
        WXTextObject textObject = new WXTextObject();
        textObject.text = "123";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = "4567";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;

        MyApplication.mWxApi.sendReq(req);
        Toast.makeText(this, "WeixinShare------->", Toast.LENGTH_SHORT).show();
    }

    public void shareSdkLogin(View view) {
//        ShareSDKUtils.Login(Wechat.NAME);
//        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
//        wechat.SSOSetting(true);
//        if (!wechat.isClientValid()) {
//            Toast.makeText(this,
//                    "微信未安装,请先安装微信",
//                    Toast.LENGTH_LONG).show();
//        }
//        authorize(wechat);
    }
    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }
        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(false);
        plat.showUser(null);
    }

    //回调：授权成功
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
//            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
            // 业务逻辑处理：比如登录操作
            String userName = platform.getDb().getUserName(); // 用户昵称
            String userId = platform.getDb().getUserId();   // 用户Id
            String platName = platform.getName();     // 平台名称
            login(platName, userId, res);
        }

    }

    @Override
    public void onError(Platform platform, int action, Throwable throwable) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
    }

    private void login(String plat, String userId, HashMap<String, Object> res) {
        Toast.makeText(this, "用户ID:" + userId, Toast.LENGTH_SHORT).show();
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        msg.obj=userId;
        msg.obj=res;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {

            case MSG_USERID_FOUND:
                Toast.makeText(this, "用户信息已存在，正在跳转登录操作......", Toast.LENGTH_SHORT).show();
                break;
            case MSG_LOGIN:
                Toast.makeText(this, "使用微信帐号登录中...", Toast.LENGTH_SHORT).show();
                break;
            case MSG_AUTH_CANCEL:
                Toast.makeText(this, "授权操作已取消", Toast.LENGTH_SHORT).show();

                break;
            case MSG_AUTH_ERROR:
                Toast.makeText(this, "授权操作遇到错误，请阅读Logcat输出", Toast.LENGTH_SHORT).show();
                break;
//            case MSG_AUTH_COMPLETE:
//                Toast.makeText(this, "授权成功，正在跳转登录操作…", Toast.LENGTH_SHORT).show();
//                // 执行相关业务逻辑操作，比如登录操作
//                String userName = new Wechat().getDb().getUserName(); // 用户昵称
//                String userId = new Wechat().getDb().getUserId();   // 用户Id
//                String platName = new Wechat().getName();      // 平台名称
//                login(platName, userId, null);
//                break;
        }
        return false;
    }

    public void shareSdkShare(View view) {

        ShareSDKUtils.shareWX("微信分享测试标题sharesdk","微信分享测试内容sharesdk",picurl,null);
//        OnekeyShare oks=new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        oks.setTitle("我的分享");
//        oks.setText("我是分享文本");
//        oks.setUrl("http://sharesdk.cn");
//        // 启动分享GUI
//        oks.show(this);
    }
    String picurl="http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg";
    private void showShare(String title,String text,String picurl) {
        OnekeyShare oks = new OnekeyShare(); //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        if(picurl!=null){
            oks.setImageUrl(picurl);
        }
        // 启动分享
        oks.show(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showShare:
                showShare("sharesdk测试","sharesdk测试",picurl);
                break;
        }
    }
}
