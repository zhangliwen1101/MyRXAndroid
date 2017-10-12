package com.testview.liuyafei.rxjavademo;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 作者：liuyafei on 2017/8/18 16:02
 * 邮箱：....com
 */

public interface Api {

//
    @GET("gouwuche.json")
    Observable<List<Course>> getCourses();


}
