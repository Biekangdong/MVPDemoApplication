package com.example.administrator.base;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public interface Presenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
