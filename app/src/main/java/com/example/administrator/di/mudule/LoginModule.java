package com.example.administrator.di.mudule;

import android.content.Context;
import android.view.View;

import com.example.administrator.interf.contract.LoginContract;
import com.example.administrator.module.LoginModleImp;
import com.example.administrator.presenter.LoginPresenter;
import com.example.administrator.view.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
@Module
public class LoginModule {
    LoginPresenter loginPresenter;
    public LoginModule(){}
    public LoginModule(LoginActivity activity){
        loginPresenter=new LoginPresenter(activity);
    }
    @Provides
    LoginModleImp getLoginModleImp(){
        return  new LoginModleImp();
    }
    @Provides
    LoginPresenter getLoginPresenter(){
        return  loginPresenter;
    }
}
