package com.example.dk.mytest.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.dk.mytest.R;

/**
 * Created by dk on 2017/6/29.
 */

public class MakeSureDialog extends Dialog {
    private TextView ok_btn;
    private TextView cancel_btn;
    private TextView content_tv;
    public MakeSureDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.make_sure_dialog_lay);
        ok_btn= (TextView) findViewById(R.id.sure_tv);
        cancel_btn= (TextView) findViewById(R.id.cancel_tv);
        content_tv= (TextView) findViewById(R.id.make_sure_dialog_content);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setCancelable(false);

        Window win=getWindow();
        win.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lay=win.getAttributes();
        lay.width=WindowManager.LayoutParams.WRAP_CONTENT;
        lay.gravity= Gravity.CENTER_VERTICAL;
        win.setAttributes(lay);
    }
    //设置标题内容
    public MakeSureDialog setContent(String content) {
        if (!TextUtils.isEmpty(content))
        content_tv.setText(content);
        return this;
    }
    //默认左键为确认键
    public MakeSureDialog setOnSureClickListener(View.OnClickListener listener) {
        if (listener!=null){
            ok_btn.setOnClickListener(listener);
        }
        return this;
    }
    //默认右键为取消键
    public MakeSureDialog setOnCancelClickListener(View.OnClickListener listener) {
        if (listener!=null){
            cancel_btn.setOnClickListener(listener);
        }
        return this;
    }
}
