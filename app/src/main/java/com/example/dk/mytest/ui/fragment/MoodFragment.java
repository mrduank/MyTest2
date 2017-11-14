package com.example.dk.mytest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.BaseViewPagerAdapter;
import com.example.dk.mytest.base.Contsts;
import com.example.dk.mytest.view.AutoViewPager;
import com.example.dk.mytest.view.ViewPagerPointView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodFragment extends Fragment {
    private AutoViewPager pager;
    private BaseViewPagerAdapter adapter;
    private List<ImageView> views ;

    private ViewPagerPointView pointView;

    private static final int[] images = {
            R.drawable.splash0,
            R.drawable.splash1,
            R.drawable.splash2,
            R.drawable.splash3,
            R.drawable.splash4,
//            R.drawable.splash5,
//            R.drawable.splash6,
//            R.drawable.splash7,
//            R.drawable.splash8,
//            R.drawable.splash9,
//            R.drawable.splash10,
//            R.drawable.splash11,
//            R.drawable.splash12,
//            R.drawable.splash13,
//            R.drawable.splash14,
//            R.drawable.splash15,
//            R.drawable.splash16,
    };

    public MoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mood, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        views=new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView img=new ImageView(getActivity());
            img.setImageResource(images[i]);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(img);
        }
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_mood).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contsts.startMoodActivity(getActivity());
            }
        });
//        pager= (AutoViewPager) view.findViewById(R.id.auto_viewpager);
        pointView= (ViewPagerPointView) view.findViewById(R.id.viewPager_point);
        pager=pointView.getViewPager();
        adapter=new BaseViewPagerAdapter(getActivity(),views);
//        adapter.init(pager,adapter);
        pointView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pager.onDestroy();
    }
}
