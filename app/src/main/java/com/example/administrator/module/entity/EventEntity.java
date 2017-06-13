package com.example.administrator.module.entity;

/**
 * Created by Administrator on 2017/4/15 0015.
 */

public class EventEntity<T> {
    String tag;
    T Message;

    public EventEntity(String tag, T Message) {
        this.tag=tag;
        this.Message=Message;
    }

    public T getMessage() {
        return Message;
    }

    public void setMessage(T message) {
        Message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
