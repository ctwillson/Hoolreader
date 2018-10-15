package com.hooltech.reader.model;

import android.util.Log;

import com.hooltech.reader.bean.BookDetailBean;
import com.hooltech.reader.bookapi.RemoteRepository;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookDetail {
    private static final String TAG = "BookDetailPresenter";
    private String bookId;

    public void refreshBookDetail(String bookId) {
        this.bookId = bookId;
        refreshBook();
//        refreshComment();
//        refreshRecommend();

    }


    private void refreshBook(){
        RemoteRepository
                .getInstance()
                .getBookDetail(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BookDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe");
                    }

                    @Override
                    public void onSuccess(BookDetailBean value){
                        Log.d(TAG,"onSuccess");
                        Log.d(TAG,value.longIntro);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError" + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }
}
