package com.example.administrator.module.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import com.example.administrator.app.AppApplication;
import com.example.administrator.app.AppContext;
import com.example.administrator.module.entity.UserInfo;
import com.example.administrator.module.retrofitclient.ApiService;
import com.example.administrator.module.retrofitclient.BaseSubscriber;
import com.example.administrator.module.retrofitclient.HttpResponse;
import com.example.administrator.module.retrofitclient.RetrofitClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class Api {
    private static  Api sNewInstance;
    ApiService apiService;
   String _account,_password;
//    public Api() {
//        apiService= RetrofitClient.getInstance(true).getApiService();
//    }
//    public Api(String url) {
//        apiService=RetrofitClient.getInstance(url,true).getApiService();
//    }
    public HashMap<String, Object> getBaseMap() {
        _account= AppContext.getInstance(AppApplication.context()).getProperty("_account");
        _password=AppContext.getInstance(AppApplication.context()).getProperty("_password");
        HashMap<String, Object> map = new HashMap<>();
        map.put("_account", _account);
        map.put("_password",_password);
        map.put("ajax",true);
        map.put("_device", "Android");
        return map;
    }
 //测试账号
//    public HashMap<String, Object> getBaseMap() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("_account", "18337183340");
//        map.put("_password","c56d0e9a7ccec67b4ea131655038d604");
//        map.put("ajax",true);
//        map.put("_device", "Android");
//        return map;
//    }

    public  HashMap<String, Object> getBaseMapRegiste() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ajax",true);
        return map;
    }
    public static Api getInstance() {
        if (sNewInstance == null) {
            sNewInstance = new Api();
        }
        return sNewInstance;
    }

    //post map请求 登录
    public void postMapLogin( String phone ,String password , BaseSubscriber<HttpResponse<UserInfo>> subscriber) {
        String url="login/";
        Map<String, Object> parameters =getBaseMapRegiste();
        parameters.put("phone",phone);
        parameters.put("password",password);
        apiService.executePostMapLogin(url,parameters)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
