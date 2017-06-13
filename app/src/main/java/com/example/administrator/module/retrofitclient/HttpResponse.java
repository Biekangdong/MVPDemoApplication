package com.example.administrator.module.retrofitclient;


import com.google.gson.annotations.SerializedName;

/**
 * 网络返回基类 支持泛型
 * Created by Tamic on 2016-06-06.
 */
public class HttpResponse<T> {
    int status;
    private String info;
    private String code;
    @SerializedName("return")
    public T returnX;

    int page_totals;
    int page_rows;
    int page_pages;

    String id;//订单id

    public T obj;//返回图片参数
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getReturnX() {
        return returnX;
    }

    public void setReturnX(T returnX) {
        this.returnX = returnX;
    }

    public int getPage_totals() {
        return page_totals;
    }

    public void setPage_totals(int page_totals) {
        this.page_totals = page_totals;
    }

    public int getPage_rows() {
        return page_rows;
    }

    public void setPage_rows(int page_rows) {
        this.page_rows = page_rows;
    }

    public int getPage_pages() {
        return page_pages;
    }

    public void setPage_pages(int page_pages) {
        this.page_pages = page_pages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
