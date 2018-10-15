package com.hooltech.reader.bookapi;

import com.hooltech.reader.bean.BookDetailBean;
import com.hooltech.reader.bean.ChapterInfoBean;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class RemoteRepository {
    private static final String TAG = "RemoteRepository";

    private static RemoteRepository sInstance;
    private Retrofit mRetrofit;
    private BookApi mBookApi;

    private RemoteRepository(){
        mRetrofit = RemoteHelper.getInstance()
                .getRetrofit();

        mBookApi = mRetrofit.create(BookApi.class);
    }

    public static RemoteRepository getInstance(){
        if (sInstance == null){
            synchronized (RemoteHelper.class){
                if (sInstance == null){
                    sInstance = new RemoteRepository();
                }
            }
        }
        return sInstance;
    }

    /***************************************书籍详情**********************************************/
    public Single<BookDetailBean> getBookDetail(String bookId){
        return mBookApi.getBookDetail(bookId);
    }

    /**
     * 注意这里用的是同步请求
     * @param url
     * @return
     */
    public Single<ChapterInfoBean> getChapterInfo(String url){
        return mBookApi.getChapterInfoPackage(url)
                .map(bean -> bean.getChapter());
    }
}
