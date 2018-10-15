package com.hooltech.reader.loginapi;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//自建服务器 用于注册、登录api
public interface hoolapi {
    @GET("/")
    Observable<String> getCall();

    @FormUrlEncoded
    @POST("/user")
    Observable<String> login(@Field("username")String name, @Field("password")String password);

    @FormUrlEncoded
    @POST("/register")
    Observable<String> register(@Field("username")String name, @Field("password")String password);
}