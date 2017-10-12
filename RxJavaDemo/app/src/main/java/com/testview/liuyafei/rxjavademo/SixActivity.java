package com.testview.liuyafei.rxjavademo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;


/**
 * 作者：liuyafei on 2017/8/24 10:33
 * 邮箱：....com
 *
 *
 * 10.购物车合并本地和网络数据的案例
 */

public class SixActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;
    Api api;
    long DEFAULT_TIMEOUT=50;
    private static final String TAG = "SixActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.six_act);
        ButterKnife.bind(this);



        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //
//        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://apicloud.mob.com/").
//                addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://bbs.liuyafei.com/zhbj/jsontest/").
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {

        Observable.merge(getDatasFromNetWork(),getDatasFromLocal())
//                .subscribe(new Consumer<List<Course>>() {
//                    @Override
//                    public void accept(List<Course> courses) throws Exception {
//                        for (Course course : courses) {
//                            Log.i(TAG, "accept: "+course.getName());
//                        }
//                    }
//                });
        .subscribe(new rx.Subscriber<List<Course>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Course> courses) {
                for (Course course : courses) {
                            Log.i(TAG, "accept: "+course.getName());
                        }
            }
        });


    }

    private Observable<List<Course>> getDatasFromLocal(){
        //从本地返回的List集合
        List<Course> list = new ArrayList<>();
        list.add(new Course("菜鸟商城"));
        list.add(new Course("菜鸟新闻"));

        return Observable.just(list);

    };


    private Observable<List<Course>> getDatasFromNetWork() {

/**
 *
 * 从网路端返回的List集合\
 * 网络端数据格式如下


  [
 {
 "id": 1,
 "name": "liuyafei"
 },
 {
 "id": 2,
 "name": "guanboyu"
 }
 ]



 */

        return api.getCourses().subscribeOn(Schedulers.io());

    }
}
