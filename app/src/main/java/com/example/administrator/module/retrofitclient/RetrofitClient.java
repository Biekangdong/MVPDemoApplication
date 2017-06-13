package com.example.administrator.module.retrofitclient;

import android.content.Context;
import android.text.TextUtils;


import com.example.administrator.app.AppApplication;
import com.example.administrator.module.aes.AesConverterFactory;
import com.example.administrator.module.entity.UserInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RetrofitClient
 * Created by Tamic on 2016-06-15.
 * {@link # https://github.com/NeglectedByBoss/RetrofitClient}
 */
public class RetrofitClient {
    private ApiService apiService;

    private OkHttpClient okHttpClient;
    private static RetrofitClient mInstance;

    public static String baseUrl = ApiService.Base_URL;

    public static RetrofitClient getmInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public RetrofitClient() {
        Cache cache = new Cache(AppApplication.context().getCacheDir(), 50 * 1024 * 1024);
        //添加拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                //设置请求读写的超时时间
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                //自定义AES解密
                .addConverterFactory(true ? AesConverterFactory.create() : GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public HashMap<String, Object> getBaseMapRegiste() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ajax", true);
        return map;
    }

    //post map请求 登录
    public void postMapLogin(String phone, String password, BaseSubscriber<HttpResponse<UserInfo>> subscriber) {
        String url = "login/";
        Map<String, Object> parameters = getBaseMapRegiste();
        parameters.put("phone", phone);
        parameters.put("password", password);
        apiService.executePostMapLogin(url, parameters)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
