package com.example.administrator.di.component;

import com.example.administrator.di.mudule.LoginModule;
import com.example.administrator.module.LoginModleImp;
import com.example.administrator.presenter.LoginPresenter;
import com.example.administrator.view.activity.LoginActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {
     LoginModleImp getLoginModleImp();
     LoginPresenter getLoginPresenter();
     // 注入
     void inject(LoginActivity activity);
}
