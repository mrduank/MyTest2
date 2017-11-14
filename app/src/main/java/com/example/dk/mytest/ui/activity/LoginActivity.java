package com.example.dk.mytest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.dialog.MakeSureDialog;
import com.example.dk.mytest.view.TitleView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TitleView titleView;
    private EditText account_et,psw_et;
    private TextView login,foget_psw,register;
    private ImageView down_img;

    private MakeSureDialog sureDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        titleView= (TitleView) findViewById(R.id.login_title);
        account_et= (EditText) findViewById(R.id.account);
        psw_et= (EditText) findViewById(R.id.psw);
        login= (TextView) findViewById(R.id.login);
        foget_psw= (TextView) findViewById(R.id.foget_psw);
        register= (TextView) findViewById(R.id.register);
        down_img= (ImageView) findViewById(R.id.down_img);

        getSureDialog();

        login.setOnClickListener(this);
        foget_psw.setOnClickListener(this);
        register.setOnClickListener(this);
        titleView.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getSureDialog() {
        sureDialog=new MakeSureDialog(this);
        sureDialog.setContent("您确定要登录吗？");
        sureDialog.setOnSureClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                sureDialog.dismiss();
            }
        });
        sureDialog.setOnCancelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sureDialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                sureDialog.show();
                break;
            case R.id.foget_psw:
                Toast.makeText(this, "忘记密码！", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,DeviceActivity.class);
                this.startActivity(intent);
                break;
            case R.id.register:
                //SwipeMenuLayout的使用（侧滑）
                Toast.makeText(this, "注册！", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this,Device2Activity.class);
                this.startActivity(i);
                break;
        }
    }
}
