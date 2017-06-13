package com.example.administrator.interf.contract;

import android.app.Activity;

import com.example.administrator.base.BaseModel;
import com.example.administrator.base.BaseView;
import com.example.administrator.interf.listener.OnLoginListener;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public interface LoginContract {
    interface LoginView extends BaseView {
        String getUserName();
        String getPassWord();
        void loginSuccess();
        void loginFaile();
        void toMainActivity();
        void showToast(String message);
        void finish();
    }
    interface  LoginModel extends BaseModel {
        void loginResult(HashMap<String,String> hashMap,OnLoginListener loginListener);
    }
}
