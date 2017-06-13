package com.example.administrator.base;

import java.lang.ref.WeakReference;

/**
 * Created by jiujiu on 2016/11/2.
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {
    protected WeakReference<V> mViewRef; //View接口类型的弱引用
    /**
     * 建立关联
     * @param view
     */
    public void attachView(V view){
        mViewRef=new WeakReference<V>(view);
    }
    /**
     * 解除关联
     */
    public void detachView(){
        if (mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }
    /**
     * 判断是否与View建立关联
     * @return
     */
    public boolean isViewAttach(){
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 获取View
     * @return
     */
    protected V getView(){
        return mViewRef.get();
    }

}
