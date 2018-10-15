package com.hooltech.reader.model;

import android.util.Log;
import android.widget.Toast;

import com.hooltech.reader.loginapi.BaseNet;
import com.hooltech.reader.loginapi.hoolapi;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class login extends BaseNet {
    private final String URL = "http://172.16.136.154:8888/";
    private final String TAG = "Reader";
    public void RegisterLogin(final String name, final String password){
        final hoolapi request = getRetrofitObject(URL).create(hoolapi.class);
        request.register(name,password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //先根据注册的响应结果去做一些操作
                Log.d(TAG,"注册结果" + s);
                if(s.equals("0")){
                    //对于这种情况，直接抛出异常，当然，这并不是优雅的解决方法
                    //细致处理可以参考 https://blog.csdn.net/jdsjlzx/article/details/51882661
                    throw new RuntimeException("haha");
                }
            }
        })
        .observeOn(Schedulers.io())
        .flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return request.login(name,password);
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG,"登录成功");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG,"登录异常");
            }
        });
    }
    public void login(String name,String password){
        final hoolapi request = getRetrofitObject(URL).create(hoolapi.class);
        request.login(name, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG,"登录结果"+s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG,"登录失败"+throwable.toString());
                    }
                });
    }
}
