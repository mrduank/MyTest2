package com.example.dk.mytest.dialog;

import android.app.Dialog;
import android.content.Context;

import com.example.dk.mytest.R;

/**
 * Created by dk on 2017/6/28.
 */

public class AnimationDialog extends Dialog {
    public AnimationDialog(Context context) {
        this(context,R.style.am_emptry_dialog);

    }

    public AnimationDialog(Context context, int themeResId) {
        super(context, themeResId);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.animation_progress_dialog);
    }


}
