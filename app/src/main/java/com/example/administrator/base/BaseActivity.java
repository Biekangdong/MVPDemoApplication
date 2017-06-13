package com.example.administrator.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.R;
import com.example.administrator.app.AppManager;
import com.example.administrator.util.DialogHelp;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * mvp activity 基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView{
    private Unbinder unBinder;
    private ProgressDialog _waitDialog;
    private boolean _isVisible;
    protected T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder= ButterKnife.bind(this);
        _isVisible = true;
        mPresenter=createPresneter();
        //关联view
        if (mPresenter != null)
            mPresenter.attachView(this);
        //添加进入集合便于处理
        init();
        AppManager.getAppManager().addActivity(this);
    }
    protected abstract T createPresneter();
    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 布局ID
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    public ProgressDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    @Override
    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注解
        unBinder.unbind();
        //解除
        if (mPresenter != null)
            mPresenter.detachView();
        //移除activity
        AppManager.getAppManager().finishActivity(this);
    }
    @Override
    public ProgressDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelp.getWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
