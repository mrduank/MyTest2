package com.example.dk.mytest.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.dk.mytest.ui.activity.GridActivity;
import com.example.dk.mytest.ui.activity.ImageBrowseActivity;
import com.example.dk.mytest.ui.activity.LoginActivity;
import com.example.dk.mytest.ui.activity.MoodActivity;
import com.example.dk.mytest.ui.activity.SwipeCardActivity;

import java.util.ArrayList;

/**
 * Created by dk on 2017/7/4.
 */

public class Contsts {
    public static void startImageBrowseActivity(Activity context, ArrayList<String> images, int position) {
        Intent intent=new Intent(context, ImageBrowseActivity.class);
        intent.putStringArrayListExtra("images",images);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }
    public static void  startGridActivity(Context context){
        Intent intent=new Intent(context,GridActivity.class);
        context.startActivity(intent);
    }
    public static void  startSwipeCardActivity(Context context){
        Intent intent=new Intent(context,SwipeCardActivity.class);
        context.startActivity(intent);
    }
    public static void  startMoodActivity(Context context){
        Intent intent=new Intent(context,MoodActivity.class);
        context.startActivity(intent);
    }
    public static void  startLoginActivity(Context context){
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }
}
