package com.example.administrator.view.fragment;

import android.content.Intent;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.base.BaseFragment;
import com.example.administrator.base.BasePresenter;
import com.example.administrator.view.activity.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/13.
 */

public class Page4Fragment extends BaseFragment{
    @Override
    protected BasePresenter createPresneter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.btn_login})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.btn_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_page4;
    }
}
