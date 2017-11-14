package com.example.dk.mytest.bean;

/**
 * Created by dk on 2017/8/16.
 */

public class MenuItems {
    private String title;
    private int resourceId;

    public MenuItems(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
