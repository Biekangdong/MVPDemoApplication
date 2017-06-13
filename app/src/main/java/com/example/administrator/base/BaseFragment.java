package com.example.administrator.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.R;
import com.example.administrator.util.DialogHelp;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * mvp activity 基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    private Unbinder unBinder;
    private ProgressDialog _waitDialog;
    private boolean _isVisible;
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(getLayoutId(), null);
        unBinder = ButterKnife.bind(this, mView);
        _isVisible = true;
        mPresenter = createPresneter();
        //关联view
        if (mPresenter != null)
            mPresenter.attachView(this);
        init();
        return mView;
    }

    protected abstract T createPresneter();

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 布局ID
     *
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
    public void onDestroy() {
        super.onDestroy();
        //解除注解
        unBinder.unbind();
        //解除
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public ProgressDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelp.getWaitDialog(getActivity(), message);
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
