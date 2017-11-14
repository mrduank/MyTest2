package com.example.dk.mytest.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.dk.mytest.R;
import com.example.dk.mytest.customprogress.CustomProgress;
import com.example.dk.mytest.utils.ImageLoaderUtils;
import com.example.dk.mytest.utils.ImgUtils;
import com.example.dk.mytest.utils.LogUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.tsy.sdk.myokhttp.util.LogUtils;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by dk on 2017/7/4.
 */

public class ViewPagerAdapter extends PagerAdapter{
    private Context context;
    private List<String> images;
    private SparseArray<View> cacheView;
    private ViewGroup containerTemp;
    public static Object tag = new Object();
    private View view;
    private ImageView image;
    private DisplayImageOptions initOptions;
    private PopupWindow popupWindow;
    private String imageUri;
//    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public ViewPagerAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        cacheView=new SparseArray<>(images.size());
        initOptions= ImageLoaderUtils.initOptions();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
//        if (containerTemp == null) containerTemp = container;

        view = cacheView.get(position);
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.vp_item_image,container,false);
            view.setTag(position);
             image = (ImageView) view.findViewById(R.id.image);
            final CustomProgress progress= (CustomProgress) view.findViewById(R.id.custom_progress);
            final PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(image);
//            ImageLoader.getInstance().clearDiskCache();
//            ImageLoader.getInstance().clearMemoryCache();
            ImageLoader.getInstance().displayImage(images.get(position), image,initOptions, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                    progress.setProgress(0);
                    progress.setVisibility(View.VISIBLE);

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    progress.setVisibility(View.GONE);
                    photoViewAttacher.update();

                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            }, new ImageLoadingProgressListener() {
                @Override
                public void onProgressUpdate(String imageUri, View view, int current, int total) {
                    LogUtils.e("current=======", String.valueOf(current));
                    LogUtils.e("total=======", String.valueOf(total));
                    progress.setProgress(current);
                    progress.setmTotalProgress(total);
                }
            });
            photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    Activity activity = (Activity) context;
                    activity.finish();
                }
            });
            //长按事件
            photoViewAttacher.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    imageUri=images.get(position);
                    LogUtil.e("OnLongClick-----------imageUri",imageUri);
                    showPop();
                    return false;
                }
            });
            cacheView.put(position,view);
        }
        container.addView(view);
        return view;
    }



    private void showPop() {
        LayoutInflater inflater=LayoutInflater.from(context);
        View popView=inflater.inflate(R.layout.pop_save,null);
        popupWindow=new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popView.findViewById(R.id.save_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("onClick-----------imageUri",imageUri);
                // 开启一个子线程，进行网络操作，等待有返回结果，使用handler通知UI
                new Thread(networkTask).start();
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        popView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(inflater.inflate(R.layout.activity_image_browser,null), Gravity.BOTTOM,0,0);
    }

    Runnable networkTask=new Runnable() {
        @Override
        public void run() {
            Bitmap bitmap=ImgUtils.getImageFromNet(imageUri);
            LogUtil.e("networkTask-----------bitmap", String.valueOf(bitmap));
            Message msg=new Message();
            msg.obj=bitmap;
            handler.sendMessage(msg);
        }
    };
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap= (Bitmap) msg.obj;
            LogUtil.e("handler-----------bitmap", String.valueOf(bitmap));
            if (bitmap != null) {
                ImgUtils.saveJPGFile(context,bitmap);
                }
        }
    };

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
