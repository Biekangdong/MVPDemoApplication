package com.example.administrator.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.base.BasePresenter;

/**
 * Created by Administrator on 2017/5/23.
 * 启动页
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
    @Override
    protected BasePresenter createPresneter() {
        return null;
    }

    @Override
    protected void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        }, 3000);
    }


}
