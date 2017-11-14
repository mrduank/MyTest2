package com.example.dk.mytest.dialog;

import android.app.Dialog;
import android.content.Context;

import com.example.dk.mytest.R;

/**
 * Created by dk on 2017/6/28.
 */

public class ProgressDialog extends Dialog {
    public ProgressDialog(Context context) {
        this(context, R.style.emptry_dialog);
    }

    public ProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.progress_dialog_layout);

    }
}
