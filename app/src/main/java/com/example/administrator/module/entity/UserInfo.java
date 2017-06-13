package com.example.administrator.module.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class UserInfo implements Serializable{
    String _id;
    String phone;
    String parent_id;
    String password;
    String date;
    String time;
    int   is_partner;//是否是股东
    String photo;
    int province_id;
    String city_id;
    String province;
    String city;

    String recharge_money;//用户交易多少钱、
    String save_money;//用户节省多少钱

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIs_partner() {
        return is_partner;
    }

    public void setIs_partner(int is_partner) {
        this.is_partner = is_partner;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRecharge_money() {
        return recharge_money;
    }

    public void setRecharge_money(String recharge_money) {
        this.recharge_money = recharge_money;
    }

    public String getSave_money() {
        return save_money;
    }

    public void setSave_money(String save_money) {
        this.save_money = save_money;
    }
}
