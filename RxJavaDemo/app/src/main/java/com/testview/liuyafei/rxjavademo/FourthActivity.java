package com.testview.liuyafei.rxjavademo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;


import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * 作者：liuyafei on 2017/8/23 15:19
 * 邮箱：....com
 * <p>
 * 使用Rxjava创建的-----8.关键词搜索案例
 */

public class FourthActivity extends AppCompatActivity {
    @BindView(R.id.Edittext)
    EditText Edittext;
    private static final String TAG = "FourthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fourth);
        ButterKnife.bind(this);
        RxTextView.textChanges(this.Edittext).debounce(200, TimeUnit.MILLISECONDS).

                subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        //过滤数据
                        Log.i(TAG, "test: " + charSequence);
                        return charSequence.toString().trim().length() > 0;
                    }
                })
                //使用switchMap代替flatMap解决返回信息覆盖的问题
                .switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(@NonNull CharSequence charSequence) throws Exception {
                        Log.i(TAG, "flatMap: " + charSequence);
                        //模拟服务端返回的数据
                        List<String> list = new ArrayList<String>();
                        list.add("abd");
                        list.add("ada");
                        return Observable.just(list);
                    }
                })
//                .flatMap(new Function<CharSequence, ObservableSource<List<String>>>() {
//                    @Override
//                    public ObservableSource<List<String>> apply(@NonNull CharSequence charSequence) throws Exception {
//
//                        Log.i(TAG, "flatMap: "+charSequence);
//                        //模拟服务端返回的数据
//                        List<String> list=new ArrayList<String>();
//                        list.add("abd");
//                        list.add("ada");
//                        return Observable.just(list);
//                    }
//                })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        Log.i(TAG, "subscribe: " + strings);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
