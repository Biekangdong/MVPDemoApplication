package com.example.administrator.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.base.BasePresenter;
import com.example.administrator.di.component.DaggerLoginComponent;
import com.example.administrator.di.component.LoginComponent;
import com.example.administrator.di.mudule.LoginModule;
import com.example.administrator.interf.contract.LoginContract;
import com.example.administrator.module.entity.EventEntity;
import com.example.administrator.presenter.LoginPresenter;
import com.example.administrator.util.RxBus;
import com.example.administrator.util.RxBusUtils;
import com.example.administrator.util.ToastUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {
    @BindView(R.id.met_name)
    MaterialEditText met_name;
    @BindView(R.id.met_password)
    MaterialEditText met_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @Inject
    LoginPresenter loginPresenter;
    LoginComponent component;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter createPresneter() {
        //loginPresenter = new LoginPresenter(this);
        component= DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build();
        //loginPresenter=component.getLoginPresenter();
        component.inject(this);
        return loginPresenter;
    }

    @Override
    protected void init() {
    }

    @OnClick({R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginPresenter.loginAction();
                break;
        }
    }


    @Override
    public String getUserName() {
        return  met_name.getText().toString();
    }

    @Override
    public String getPassWord() {
        return met_password.getText().toString();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFaile() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toMainActivity() {
        RxBus.get().post("aa", "传递数据++++++");
        RxBusUtils.getInstance().post(new EventEntity<String>("aaa", "yoyo"));
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void showToast(String message) {
        ToastUtils.show(this,message);
    }

    @Override
    public void finish() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
