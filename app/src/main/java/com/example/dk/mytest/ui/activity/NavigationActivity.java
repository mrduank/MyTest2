package com.example.dk.mytest.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.base.Contsts;
import com.example.dk.mytest.ui.fragment.HomeFragment;
import com.example.dk.mytest.ui.fragment.MoodFragment;
import com.example.dk.mytest.utils.LogUtil;
import com.example.dk.mytest.view.CircleImageView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private CircleImageView userIcon;
    private TextView userName;
    private PopupWindow pop;
    private AlertDialog dialog;

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;//结果
    private File tempFile = null;
    private static final int REQUEST_CODE_CHOOSE=4;
    private List<Uri> mSelected;
    private static final String  authority="com.example.fileprovider";
    private View drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initViews();
        hideScrollBar();
        setActionBar();
        setDrawerToggle();
        setListener();
        if (savedInstanceState==null){
            Log.e("navig===", "onCreat");
            replaceFragment(new HomeFragment());
        }
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout_nav);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        toolbar.setTitle("NavigationView");
        toolbar.setTitleTextColor(Color.WHITE);
        //加载navigation的头布局,实现点击事件
       drawView = navigationView.inflateHeaderView(R.layout.navigation_head);
        userIcon = (CircleImageView) drawView.findViewById(R.id.user_icon);
        userName = (TextView) drawView.findViewById(R.id.user_name);
        userIcon.setOnClickListener(this);
        userName.setOnClickListener(this);
        drawView.setOnClickListener(this);

    }

    private void showSelectDialog() {
        dialog = new AlertDialog.Builder(this).create();
        View popView = getLayoutInflater().inflate(R.layout.pop_photo, null);
        popView.findViewById(R.id.take_photo).setOnClickListener(this);
        popView.findViewById(R.id.album).setOnClickListener(this);
        popView.findViewById(R.id.cancel).setOnClickListener(this);
        dialog.setView(popView, 0, 0, 0, 0);
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();//得到这个dialog界面的参数对象
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;//设置dialog高度为包裹内容
        params.width = WindowManager.LayoutParams.MATCH_PARENT;//设置dialog高度为包裹内容
        params.gravity = Gravity.BOTTOM;//设置dialog的重心
        dialog.getWindow().setAttributes(params);//最后把这个参数对象设置进去，即与dialog绑定

    }

    private void showPop() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View popView = inflater.inflate(R.layout.pop_photo, null);
        Display display = getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
        Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
        display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
        int width = size.x;//从Point点对象中获取屏幕的宽度(单位像素)
        int height = size.y;//从Point点对象中获取屏幕的高度(单位像素)
        pop = new PopupWindow(popView, 14 * width / 15, WindowManager.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        //动画
        pop.setAnimationStyle(R.style.Popupwindow);
        int[] location = new int[2];
        popView.getLocationOnScreen(location);
        //透明度
        WindowManager.LayoutParams params = getWindow().getAttributes();//创建当前界面的一个参数对象
        params.alpha = 0.5f;//设置参数的透明度为0.8，透明度取值为0~1，1为完全不透明，0为完全透明，因为android中默认的屏幕颜色都是纯黑色的，所以如果设置为1，那么背景将都是黑色，设置为0，背景显示我们的当前界面
        getWindow().setAttributes(params);//把该参数对象设置进当前界面中
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {//设置PopupWindow退出监听器
            @Override
            public void onDismiss() {//如果PopupWindow消失了，即退出了，那么触发该事件，然后把当前界面的透明度设置为不透明
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1.0f;//设置为不透明，即恢复原来的界面
                getWindow().setAttributes(params);
            }
        });
        popView.findViewById(R.id.take_photo).setOnClickListener(this);
        popView.findViewById(R.id.album).setOnClickListener(this);
        popView.findViewById(R.id.cancel).setOnClickListener(this);
        pop.showAtLocation(inflater.inflate(R.layout.activity_navigation, null), Gravity.BOTTOM, 0, -location[1]);
    }

    private void hideScrollBar() {
        navigationView.getChildAt(0).setVerticalScrollBarEnabled(false);
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    //下述函数是来捕获ToolBar中的item点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //还可以继续添加其余item的点击事件
        return super.onOptionsItemSelected(item);
    }

    private void setListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.news:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.mood:
                        replaceFragment(new MoodFragment());
//                        replaceFragment(new ThirdFragment());
                        break;
                    case R.id.meizi:
                        Contsts.startGridActivity(NavigationActivity.this);
                        break;
                    case R.id.move:
                        Matisse.from(NavigationActivity.this)
                                .choose(MimeType.allOf()) //照片视频全部显示
                                .countable(true)//有序选择图片
                                .maxSelectable(9) // 图片选择的最多数量
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f) // 缩略图的比例
                                .theme(R.style.Matisse_Zhihu)//主题  暗色主题 R.style.Matisse_Dracula
                                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                                .capture(true) //是否提供拍照功能
                                .captureStrategy(new CaptureStrategy(true,authority))//存储到哪里
                                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
//                        replaceFragment(new FirstFragment());
                        break;
                    case R.id.setting:
                        Toast.makeText(NavigationActivity.this, "setting", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_theme:
                        Toast.makeText(NavigationActivity.this, "move", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_night:
                        Toast.makeText(NavigationActivity.this, "nav_night", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_suggestion:
                        break;
                    case R.id.nav_about:
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_view_ly:
                Contsts.startSwipeCardActivity(this);
                break;
            case R.id.user_icon:
//                Contsts.startSwipeCardActivity(this);
                showPop();
                break;
            case R.id.user_name:
                Contsts.startLoginActivity(this);
                break;
            case R.id.take_photo:
                tempFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                LogUtil.e("take_photo-----", String.valueOf(intent));
                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                if (pop.isShowing()) pop.dismiss();
                break;
            case R.id.album:
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
                if (pop.isShowing()) pop.dismiss();
                break;
            case R.id.cancel:
                Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
                if (pop.isShowing()) pop.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                LogUtil.e("onActivityResult----2", String.valueOf(Uri.fromFile(tempFile)));
                startPhotoZoom(Uri.fromFile(tempFile));
                break;
            case PHOTO_REQUEST_GALLERY:
                if (data != null) startPhotoZoom(data.getData());
                break;
            case PHOTO_REQUEST_CUT:
                if (data != null) {
                    setImageToIcon(data);
                }
                break;
            case REQUEST_CODE_CHOOSE:
                if (data!=null){
                    mSelected = Matisse.obtainResult(data);
                    Log.d("Matisse", "mSelected: " + mSelected);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setImageToIcon(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            userIcon.setImageBitmap(photo);
        }
    }

    /**
     * 调用系统照相机的图片裁剪功能
     *
     * @param uri
     */

    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 13);
        intent.putExtra("aspectY", 11);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 130);
        intent.putExtra("outputY", 110);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
