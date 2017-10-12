package com.testview.liuyafei.rxjavademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 作者：liuyafei on 2017/8/24 09:18
 * 邮箱：....com
 * <p>
 * <p>
 * <p>
 * 9.防止按钮重复点击案例
 */

public class FiveActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;
    private static final String TAG = "FiveActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_act);
        ButterKnife.bind(this);

        //也可以将throttleFirst方法换成debounce
        RxView.clicks(button).throttleFirst(1,TimeUnit.SECONDS).subscribe(new Observer<Object>() {


            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
             Log.i(TAG, "onNext: 这是按钮点击事件");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    /**
     * 一般处理方案，获取点击时间差，当时间大于某一个值的时候，执行某个方法。
     */
//    private  long lastTime=0;
//    @OnClick(R.id.button)
//    public void onViewClicked() {
//
//        long currTime=System.currentTimeMillis();
//        if (lastTime-currTime>500){
//            //执行按钮点击以后的操作
//        }
//        lastTime=currTime;
//
//
//    }
}
