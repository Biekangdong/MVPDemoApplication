package com.example.administrator.interf.listener;

import com.example.administrator.module.entity.UserInfo;

/**
 * Created by Administrator on 2017/4/15 0015.
 */

public interface OnLoginListener {
    void loginSuccess(UserInfo user);

    void loginFailed(String errorMessage);
}
