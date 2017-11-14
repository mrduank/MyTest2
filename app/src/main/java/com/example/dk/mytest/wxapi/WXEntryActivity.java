package com.example.dk.mytest.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.base.MyApplication;
import com.example.dk.mytest.utils.LogUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler{
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        //如果没回调onResp，八成是这句没有写
       MyApplication.mWxApi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MyApplication.mWxApi.handleIntent(intent,this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtil.d("WXEntryActivity----->onReq");

    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtil.d(resp.errStr);
        LogUtil.d("错误码 : " + resp.errCode + "");
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(this, "发送被拒绝...", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(this, "发送取消...", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        break;
                    case RETURN_MSG_TYPE_SHARE:
                        Toast.makeText(this, "分享成功...", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
                break;
            default:
                break;
        }
    }
}
