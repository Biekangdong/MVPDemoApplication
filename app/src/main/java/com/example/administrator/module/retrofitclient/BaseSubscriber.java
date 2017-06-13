package com.example.administrator.module.retrofitclient;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.administrator.app.AppApplication;
import com.example.administrator.util.NetworkUtil;

import rx.Subscriber;


/**
 * BaseSubscriber
 * Created by Tamic on 2016-1128.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private boolean isNeedCahe;
   public BaseSubscriber(){}
    @Override
    public void onError(Throwable e) {
        Log.e("Tamic", e.getMessage());
        // todo error somthing

        if(e instanceof ExceptionHandle.ResponeThrowable){
            onError((ExceptionHandle.ResponeThrowable)e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        //Toast.makeText(context, "http is start", Toast.LENGTH_SHORT).show();

        // todo some common as show loadding  and check netWork is NetworkAvailable
        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable()) {
            Toast.makeText(AppApplication.context(), "无网络，请稍后重试", Toast.LENGTH_SHORT).show();
//            onCompleted();
        }

    }

    @Override
    public void onCompleted() {

        //Toast.makeText(context, "http is Complete", Toast.LENGTH_SHORT).show();
        // todo some common as  dismiss loadding
    }


    public abstract void onError(ExceptionHandle.ResponeThrowable e);

}
