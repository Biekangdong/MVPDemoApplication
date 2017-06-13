package com.example.administrator.presenter;

import com.example.administrator.base.BasePresenter;
import com.example.administrator.di.component.DaggerLoginComponent;
import com.example.administrator.di.component.LoginComponent;
import com.example.administrator.di.mudule.LoginModule;
import com.example.administrator.interf.contract.LoginContract;
import com.example.administrator.interf.listener.OnLoginListener;
import com.example.administrator.module.LoginModleImp;
import com.example.administrator.module.entity.UserInfo;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class LoginPresenter extends BasePresenter<LoginContract.LoginView> {
    LoginContract.LoginModel loginModel;
    LoginContract.LoginView loginView;
    LoginComponent component;
    public LoginPresenter(LoginContract.LoginView loginView){
        //loginModel=new LoginModleImp();
        this.loginView=loginView;
        //component= DaggerLoginComponent.builder().loginMoudle(new LoginModule()).build().getLoginModleImp();
        component=DaggerLoginComponent.builder().loginModule(new LoginModule()).build();
        loginModel=component.getLoginModleImp();
    }
    public void loginAction(){
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("userName",loginView.getUserName());
        hashMap.put("passWord",loginView.getPassWord());
        loginView.showWaitDialog("正在登录...");
        loginModel.loginResult(hashMap, new OnLoginListener() {
            @Override
            public void loginSuccess(UserInfo user) {
                loginView.hideWaitDialog();
                loginView.showToast("登录成功");
                loginView.toMainActivity();
                loginView.finish();
            }

            @Override
            public void loginFailed(String errorMessage) {
                loginView.hideWaitDialog();
                loginView.showToast(errorMessage);
            }
        });
    }
}
