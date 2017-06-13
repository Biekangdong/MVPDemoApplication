package com.example.administrator.app;

import android.app.Application;
import android.content.Context;

import com.example.administrator.module.aes.Aes;
import com.example.administrator.util.StringUtils;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class AppApplication extends Application {
    static Context _context;

    @Override
    public void onCreate() {
        super.onCreate();
        _context=getApplicationContext();
        Aes.init(AppConfig.AESKEY,AppConfig.AESVALUE);
        String _account=AppContext.getInstance(_context).getProperty("_account");
        String _password=AppContext.getInstance(_context).getProperty("_password");
        if(StringUtils.isEmpty(_account)||StringUtils.isEmpty(_password)){
            AppContext.getInstance(_context).setProperty("_account","");
            AppContext.getInstance(_context).setProperty("_password","");
        }
    }
    //AppApplication
    public static synchronized AppApplication context() {
        return (AppApplication) _context;
    }
}
