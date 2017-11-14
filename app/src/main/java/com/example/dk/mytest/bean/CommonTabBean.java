package com.example.dk.mytest.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by dk on 2017/8/25.
 */

public class CommonTabBean implements CustomTabEntity {
    private int selectedIcon;
    private int unselectedIcon;
    private String title;

    public CommonTabBean(String title,int selectedIcon,  int unselectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unselectedIcon = unselectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectedIcon;
    }
}
