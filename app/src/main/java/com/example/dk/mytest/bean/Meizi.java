package com.example.dk.mytest.bean;

/**
 * Created by dk on 2017/6/30.
 */

public class Meizi {
    private String url;//图片地址
    private int page;//页数
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
