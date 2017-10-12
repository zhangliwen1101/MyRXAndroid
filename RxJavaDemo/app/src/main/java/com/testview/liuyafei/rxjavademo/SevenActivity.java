package com.testview.liuyafei.rxjavademo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;


/**
 * 作者：liuyafei on 2017/8/24 16:42
 * 邮箱：....com
 *
 *
 * 11.发送验证码倒计时案例
 */

public class SevenActivity extends AppCompatActivity {


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.editText)
    EditText editText;
    private static final String TAG = "SevenActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_act);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.button)
    public void onViewClicked() {
        final int count = 10;

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return count-aLong;   //默认倒计时显示是“剩余1,2,3,4.。。。”使用map方法将along进行转化，转化后aLong的值是count-along
                    }                         //转化的是OnNext方法中的along的值。
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //call方法相当于onNext方法----开始倒计时的时候让按钮不可点击
                        button.setEnabled(false);
                    }
                })
                .subscribe(new Subscriber<Long>() {
                               @Override
                               public void onCompleted() {
                                   Log.i(TAG, "onCompleted: ");
                                   button.setEnabled(true);
                                   button.setTextColor(Color.BLACK);
                                   button.setText("发送验证码");
                                   button.setBackgroundColor(Color.parseColor("#FF3E96"));
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.i(TAG, "onError: ");
                               }

                               @Override
                               public void onNext(Long aLong) {
                                   Log.i(TAG, "onNext: " + aLong);
                                   button.setText("剩余" + aLong + "秒");
                                   button.setTextColor(Color.RED);
                                   button.setBackgroundColor(Color.GRAY);

                               }
                           }
                );
    }
}
