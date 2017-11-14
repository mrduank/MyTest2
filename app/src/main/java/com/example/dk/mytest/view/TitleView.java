package com.example.dk.mytest.view;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dk.mytest.R;

/**
 * Created by dk on 2017/6/23.
 */

public class TitleView extends FrameLayout {
    private Context context;
    private RelativeLayout titleLayout;
    private TextView leftText;
    private ImageView leftButton;
    private TextView centerText;
    private TextView rightText;
    private ImageView rightImg;
    private ImageView centerImg;


    private View statusView;

    private int leftType = 0;//1ΪText��0ΪImageview

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.title_view_layout,this,true);
        statusView = v.findViewById(R.id.titleview_status_view_color);
        titleLayout = (RelativeLayout) v.findViewById(R.id.title_layout);
        leftText   = (TextView) titleLayout.getChildAt(0);
        leftButton = (ImageView) titleLayout.getChildAt(1);
        centerText = (TextView) titleLayout.getChildAt(2);
        centerImg = (ImageView)titleLayout.getChildAt(3);
        rightText  = (TextView) titleLayout.getChildAt(4);
        rightImg   = (ImageView) titleLayout.getChildAt(5);

        setTitleLeftDrawable(R.drawable.icon_back_3x);
        setMidleImgVisible(false);
        post(new Runnable() {
            @Override
            public void run() {
           setStatusHeight();
            }
        });
    }

    private void setStatusHeight(){
        int statusHeight = getStatusHeight();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            MarginLayoutParams llp = ((MarginLayoutParams) getLayoutParams());
            llp.height -= statusHeight;
            setLayoutParams(llp);
            statusView.setVisibility(GONE);
        }
        MarginLayoutParams lp = ((MarginLayoutParams) statusView.getLayoutParams());
        lp.height = statusHeight;
        statusView.setLayoutParams(lp);
    }
    public void setStatus(int colorRes){
        statusView.setBackgroundResource(colorRes);
        titleLayout.setBackgroundResource(colorRes);
    }
    private int getStatusHeight(){
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public void setMidleImgVisible(boolean isShow){
        if(isShow){
            centerImg.setVisibility(View.VISIBLE);
        }else {
            centerImg.setVisibility(View.GONE);
        }
    }

    public void setTitleleftText(int res){
        leftButton.setVisibility(View.GONE);
        leftText.setVisibility(View.VISIBLE);
        leftText.setText(res);
        leftType = 1;
    }

    public void setTitleOnClickListener(OnClickListener listener){
        centerText.setOnClickListener(listener);
        centerImg.setOnClickListener(listener);
    }

    public void setTitle(CharSequence cs){
        if(!TextUtils.isEmpty(cs)){
            centerText.setText(cs);
        }
    }

    public void setTitle(int res){
        centerText.setText(res);
    }

    public void setTitleVisibility(int gone){
        titleLayout.setVisibility(gone);
    }

    public void setTitleBackground(int res){
        titleLayout.setBackgroundResource(res);
    }

    public void setTitleRightText(CharSequence cs){
        if(!TextUtils.isEmpty(cs)){
            rightText.setText(cs);
            rightText.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleRightText(int res){
        rightImg.setVisibility(View.GONE);
        rightText.setVisibility(View.VISIBLE);
        setTitleRightText(context.getString(res));
    }

    public void setRightImgVisibility(int gone){

        rightImg.setVisibility(gone);

    }

    public void setRightTextVisibility(int gone){
        rightText.setVisibility(gone);
    }

    public void setTitleLeftDrawable(int res){
        leftButton.setImageResource(res);
    }
    public void setMidleDrawable(int res){
        centerImg.setImageResource(res);
    }

    public void setLeftButtonVisibility(int gone){
        leftButton.setVisibility(gone);
        leftText.setVisibility(View.GONE);
    }

    public void setLeftButtonOnClickListener(OnClickListener listener){
        if(null != listener){
            if(leftType == 0){
                leftButton.setOnClickListener(listener);
            }else{
                leftText.setOnClickListener(listener);
            }
        }
    }
    public void setRightTextOnClickListener(OnClickListener listener){
        if(null != listener){
            rightText.setOnClickListener(listener);
        }
    }

    public void setRightImg(int res){
        rightText.setVisibility(View.GONE);
        rightImg.setVisibility(View.VISIBLE);
        rightImg.setImageResource(res);
    }

    public void setRightOnClickListener(OnClickListener l){
        if(l != null){
            rightImg.setOnClickListener(l);
        }
    }
}
