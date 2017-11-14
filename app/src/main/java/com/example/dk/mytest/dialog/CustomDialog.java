package com.example.dk.mytest.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dk.mytest.R;

/**
 * Created by dk on 2017/6/28.
 */

public class CustomDialog extends Dialog {
    private ProgressBar progressBar;
    private ImageView progressImg;
    public CustomDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.animation_progress_dialog);
        progressBar= (ProgressBar) findViewById(R.id.anim_progress);
        progressImg= (ImageView) findViewById(R.id.anim_progress_img);
        progressImg.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        setCanceledOnTouchOutside(false);
        setMessage("登录中...");
        Window win = getWindow();
        win.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lay = win.getAttributes();
        lay.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lay.gravity = Gravity.CENTER_VERTICAL;
        win.setAttributes(lay);

    }


    /**
     * 当窗口焦点改变时调用
     */
    public void onWindowFocusChanged(boolean hasFocus) {
        // 获取ImageView上的动画背景
        AnimationDrawable spinner = (AnimationDrawable) progressImg.getBackground();
        // 开始动画
        spinner.start();
    }
    /**
     * 给Dialog设置提示信息
     *
     * @param message
     */
    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();//文字自动刷新
        }
    }

}
