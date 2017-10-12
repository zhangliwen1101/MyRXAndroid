package com.testview.liuyafei.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 * 作者：liuyafei on 2017/8/23 14:50
 * 邮箱：....com
 */

public class ThirdActivity extends AppCompatActivity {

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

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //对应Observer的onNext方法
                Log.i(TAG, "accept: " + s);
                text.append(s + "//n");//由打印信息知道，just方法相当于多个onnext方法，分3次将---"大保健","泡吧","撩妹"---发送给accept方法
//                text.setText(s);
            }
        });


    }

    public Observable<String> getObservable() {


//        return Observable.just("大保健","泡吧","撩妹");//just底部实际调用的是fromArray方法
//        return Observable.fromArray("大保健","泡吧","撩妹");

/**
 * 这样创建的Observable与just方法和create相比，因为call方法只能调用一次，相当于只能执行一次onnext方法
 */
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "大保健";
            }
        });


//        return Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
//                e.onNext("大保健");
//                e.onNext("泡吧");
//
//            }
//        });
    }

    ;

}
