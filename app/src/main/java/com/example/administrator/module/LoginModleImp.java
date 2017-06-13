package com.example.administrator.module;

import android.util.Log;

import com.example.administrator.app.AppContext;
import com.example.administrator.interf.contract.LoginContract;
import com.example.administrator.interf.listener.OnLoginListener;
import com.example.administrator.module.api.Api;
import com.example.administrator.module.entity.UserInfo;
import com.example.administrator.module.retrofitclient.BaseSubscriber;
import com.example.administrator.module.retrofitclient.ExceptionHandle;
import com.example.administrator.module.retrofitclient.HttpResponse;
import com.example.administrator.module.retrofitclient.RetrofitClient;
import com.example.administrator.util.StringUtils;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class LoginModleImp implements LoginContract.LoginModel{
    @Override
    public void loginResult(HashMap<String,String> hashMap,final OnLoginListener loginListener) {
        String userName=hashMap.get("userName");
        String password=hashMap.get("passWord");
        String result="";
        if(StringUtils.isEmpty(userName)){
            result="用户名不能为空";
            loginListener.loginFailed(result);
            return;
        }
        if(StringUtils.isEmpty(password)){
            result="密码不能为空";
            loginListener.loginFailed(result);
            return;
        }
       RetrofitClient.getmInstance().postMapLogin(userName, password, new BaseSubscriber<HttpResponse<UserInfo>>() {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                loginListener.loginFailed(e.message);
            }

            @Override
            public void onNext(HttpResponse<UserInfo> userInfoHttpResponse) {
                Gson gson = new Gson();
                Log.i("onNext", gson.toJson(userInfoHttpResponse));
                if (userInfoHttpResponse.getStatus() == 1) {
                    UserInfo userInfo = userInfoHttpResponse.getReturnX();
                    loginListener.loginSuccess(userInfo);
                } else {
                    loginListener.loginFailed(userInfoHttpResponse.getInfo());
                }
            }
        });
    }

    @Override
    public void clearData() {

    }
}
