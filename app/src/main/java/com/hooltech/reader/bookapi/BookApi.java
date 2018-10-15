package com.hooltech.reader.bookapi;

import com.hooltech.reader.bean.BookDetailBean;
import com.hooltech.reader.bean.packages.ChapterInfoPackage;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookApi {

    /**
     * 书籍详情
     * @param bookId
     * @return
     */
    @GET("/book/{bookId}")
    Single<BookDetailBean> getBookDetail(@Path("bookId") String bookId);

    /**
     * 章节的内容
     * 这里采用的是同步请求。
     * @param url
     * @return
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/{url}")
    Single<ChapterInfoPackage> getChapterInfoPackage(@Path("url") String url);


}
