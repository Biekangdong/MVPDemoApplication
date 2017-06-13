package com.example.administrator.module.retrofitclient;

import com.example.administrator.module.entity.UserInfo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ｔａｍｉｃ on 2016-07-08.
 * {@link # https://github.com/NeglectedByBoss/RetrofitClient}
 */
public interface ApiService {
    public  final String Http="http://";
    public  final String Host="gas.meiniucn.com";
    public  final String Url="/App/";

    public static final String Base_URL =Http+Host+Url;
    public static final String WebURL ="http://gas.meiniucn.com/AppWeb/";
    @POST("{url}")
    @FormUrlEncoded
    Observable<HttpResponse> executeGetResponseBody(
            @Path(value = "url", encoded = true) String url,
            @FieldMap Map<String, Object> maps);

    //POST请求  Form注册1
    @POST("{url}")
    @FormUrlEncoded
    Observable<HttpResponse> postFormRegister(
            @Path(value = "url", encoded = true) String url,
            @FieldMap Map<String, Object> maps);

    //POST请求  QueryMap  登录
    @FormUrlEncoded
    @POST("{url}")
    Observable<HttpResponse<UserInfo>> executePostMapLogin(
            @Path(value = "url", encoded = true) String url,
            @FieldMap Map<String, Object> maps);

    //POST请求  QueryMap 获取个人信息
    @FormUrlEncoded
    @POST("{url}")
    Observable<HttpResponse<UserInfo>> executePostMapUserInformation(
            @Path(value = "url", encoded = true) String url,
            @FieldMap Map<String, Object> maps);



    @FormUrlEncoded
    //上传多张图片
    @POST("{url}")
    Observable<HttpResponse<UserInfo>> upLoadUserImg(
            @Path(value = "url", encoded = true) String url,
            @FieldMap Map<String, Object> maps);

    //上传单张图片
    @POST("{url}")
    @Multipart
    Observable<HttpResponse<UserInfo>> upLoadUserImg(
            @Path(value = "url", encoded = true) String url,
            @Query("_device") String _device,
            @Query("_account") String _account,
            @Query("_password") String _password,
            @Query("ajax") boolean ajax,

            @Query("id") String id,
            @Query("sign") String sign,
            @Part() MultipartBody.Part part);

}
