package com.example.dk.mytest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dk.mytest.R;
import com.example.dk.mytest.adapter.CommonPagerAdapter;
import com.example.dk.mytest.bean.CommonTabBean;
import com.example.dk.mytest.duanzi.ui.DuanziFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private CommonTabLayout tabLayout;

    private List<Fragment> mFragments;
    private static final int[] SELECTED_ICONS = new int[]{R.drawable.diary_selected, R.drawable.duanzi_selected, R.drawable.meizi_selected};
    private static final int[] UN_SELECTED_ICONS = new int[]{R.drawable.diary_unselected, R.drawable.duanzi_unselected, R.drawable.meizi_unselected};
    private static final String[] TITLES = new String[]{"日记", "段子", "妹子"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.e("HomeFragment", "onCreat");
        initView(view);
        initTabLayout();
        initVierPager();
        return view;
    }

    private void initVierPager() {
        mFragments = new ArrayList<>();
        Log.e("initVierPager", "onCreat");
        mFragments.add(new SecondFragment());
        mFragments.add(new DuanziFragment());
        mFragments.add(new MeiziFragment());
        CommonPagerAdapter pagerAdapter = new CommonPagerAdapter(getFragmentManager(), mFragments);
        viewPager.setAdapter(pagerAdapter);

    }

    private void initTabLayout() {
        ArrayList<CustomTabEntity> tabEntityList = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            tabEntityList.add(new CommonTabBean(TITLES[i], SELECTED_ICONS[i], UN_SELECTED_ICONS[i]));
        }
        tabLayout.setTabData(tabEntityList);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(4);//表示4个界面之间来回切换都不会重新加载
        viewPager.setCurrentItem(1);
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.home_view_pager);
        tabLayout = (CommonTabLayout) view.findViewById(R.id.home_tab_layout);
    }

}
