package com.example.administrator.base;

import android.app.ProgressDialog;

/**
 * Created by jiujiu on 2016/11/1.
 */
public interface BaseView {
    public abstract void hideWaitDialog();

    public abstract ProgressDialog showWaitDialog();

    public abstract ProgressDialog showWaitDialog(int resid);

    public abstract ProgressDialog showWaitDialog(String text);
}
