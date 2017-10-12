package com.testview.liuyafei.rxjavademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static android.os.Build.VERSION_CODES.N;


/**
 * 作者：liuyafei on 2017/8/23 14:25
 * 邮箱：....com
 */

public class SecondActivity extends AppCompatActivity {


    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button)
    Button button;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    /**
     * Observable绑定Observer的简单写法---这也方法也可以单独使用一个
     */
    @OnClick(R.id.button)
    public void onViewClicked() {
        Observable<String> observable = getObservable();
//        Observer<String> Observer=getObserver();
//        observable.subscribe(Observer);

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //对应Observer的onNext方法
                Log.i(TAG, "accept: " + s);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                //对应Observer的onError方法
                Log.i(TAG, "accept: ");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                //对应Observer的onComplete方法
                Log.i(TAG, "run: ");
            }
        });

    }

    public Observable<String> getObservable() {

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("大保健");
                e.onNext("泡吧");
//                e.onComplete();
            }
        });
    }

    ;

//    public Observer<String> getObserver(){
//        //解除绑定
//        //d.dispose();
//        return new Observer<String>() {
//
//            Disposable dd=null;
//
//
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                dd=d;
//                Log.i(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//
//                if (s.equals("大保健")) {
//                    dd.dispose();//解除绑定
//                }
//                Log.i(TAG, "onNext: "+s);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.i(TAG, "onError: ");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i(TAG, "onComplete: ");
//            }
//        };


//    }


}
