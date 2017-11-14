package com.example.dk.mytest.ui.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.ImageGridAdapter;
import com.example.dk.mytest.base.BaseActivity;
import com.example.dk.mytest.lib.MultiColumnListView;
import com.example.dk.mytest.view.TitleView;

import java.util.ArrayList;

public class SampleActivity extends BaseActivity {
    private MultiColumnListView mAdapterView = null;
    private ArrayList<String> imageUrls;
    private ImageGridAdapter adapter;
    private TitleView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        initView();

    }

    private void initView() {
        titleView= (TitleView) findViewById(R.id.sample_title);
        titleView.setLeftButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapterView = (MultiColumnListView) findViewById(R.id.list);
        imageUrls = new ArrayList<String>();
        adapter = new ImageGridAdapter(this, imageUrls);
        mAdapterView.setAdapter(adapter);
        queryMediaImages();
    }
     //加载本地图片
    private void queryMediaImages() {
        Cursor c = getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null );
        if ( c != null ) {
            if (c.moveToFirst()) {
                do {
                    long id = c.getLong( c.getColumnIndex( MediaStore.Images.Media._ID ) );
                    Uri imageUri = Uri.parse( MediaStore.Images.Media.EXTERNAL_CONTENT_URI + "/" + id );
                    imageUrls.add(imageUri.toString());
                    //imageUrls.add(getRealFilePath(MainActivity.this, imageUri));
                } while (c.moveToNext());
            }
        }
        c.close();
        c = null;
        adapter.notifyDataSetChanged();
    }


    }

