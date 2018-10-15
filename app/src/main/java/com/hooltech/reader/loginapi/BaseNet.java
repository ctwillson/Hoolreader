package com.hooltech.reader.loginapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseNet {


    protected OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(new RetryIntercepter(1));

    protected Retrofit getRetrofitObject(String url) {
        return new Retrofit.Builder().baseUrl(url)
                //增加返回值为字符串的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder.build())
                .build();
    }

//    protected Retrofit getRetrofitString(String url, String encode) {
//        return new Retrofit.Builder().baseUrl(url)
//                //增加返回值为字符串的支持(以实体类返回)
//                .addConverterFactory(EncodoConverter.create(encode))
//                //增加返回值为Oservable<T>的支持
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(clientBuilder.build())
//                .build();
//    }

}
