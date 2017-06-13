package com.example.administrator.util;

import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2017/4/15 0015.
 */

public class RxBusUtils {
    private static volatile RxBusUtils instance = null;

    /**
     * PublishSubject只会把订阅发生的时间点之后来自原始Observable的数据发送给观察者
     */
    private Subject<Object,Object> mRxBus = new SerializedSubject<>(PublishSubject.create());

    public static RxBusUtils getInstance(){
        if (instance == null){
            synchronized (RxBus.class){
                if (instance == null){
                    instance = new RxBusUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 判断当前是否有可用的Observable
     * @return
     */
    private boolean hasObservable(){
        return mRxBus.hasObservers();
    }

    /**
     * 发送新的事件
     * @param o
     */
    public void post(Object o){
        if (hasObservable()){
            mRxBus.onNext(o);
        }
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType){
        return mRxBus.ofType(eventType);
    }

    /**
     * 取消事件订阅
     * @param subscription
     */
    public void unRegister(Subscription subscription){
        if (subscription !=null &&subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
