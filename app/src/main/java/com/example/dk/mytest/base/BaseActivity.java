package com.example.dk.mytest.base;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.dk.mytest.R;
import com.example.dk.mytest.dialog.AnimationDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by dk on 2017/6/28.
 */

public class BaseActivity extends FragmentActivity {
    private Dialog progressDialog;
    private int statusColor = R.color.bg_top;
    protected SystemBarTintManager mTintManager;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);
            mTintManager.setStatusBarTintResource(statusColor);
        }
        initDialog();
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initDialog() {
        progressDialog=new AnimationDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

    }
    public void showProgressDialog() {
        if (progressDialog!=null&&!progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    public void dissmissProgressDialog() {
        if (progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
