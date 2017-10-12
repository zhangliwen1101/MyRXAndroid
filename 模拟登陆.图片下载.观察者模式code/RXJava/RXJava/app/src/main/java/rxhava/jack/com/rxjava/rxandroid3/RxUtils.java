package rxhava.jack.com.rxjava.rxandroid3;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhangliwen on 2017/10/10.
 */

public class RxUtils {
    private static final String TAG = RxUtils.class.getSimpleName();

    /**
     * 使用create 方式
     */
    public static void createObserable() {
        //定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("hello");
                    subscriber.onNext("world");
                    subscriber.onNext(downJson());
                    subscriber.onNext("cat");
                    subscriber.onCompleted();
                }
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "is result-->" + s);
            }
        };

        observable.subscribe(subscriber);//关联被观察者
    }

    //调用下载方法
    public static String downJson() {
        return "json data";
    }

    /**
     * create 第二种创建方式
     */
    public static void cretePrint() {
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 1; i <= 10; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "is result-->" + integer);
            }
        });
    }

    /**
     * 使用在被观察者，返回的类型都是数值类型
     */
    public static void form() {
        Integer[] items = {1, 2, 3, 4, 5, 2, 6, 4};
        Observable observable = Observable.from(items);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG, o.toString());
            }
        });
    }

    /**
     * 指定某一时刻进行数据发送
     */
    public static void interval() {
        Integer[] items = {1, 2, 3, 4, 5, 2};
        Observable observable = Observable.interval(1, 1, TimeUnit.SECONDS);//每一秒发送一个数据
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG, o.toString());
            }
        });
    }

    /**
     * 处理数组集合
     */
    public static void just() {
        Integer[] items1 = {1, 4, 2, 3, 4};
        Integer[] items2 = {2, 4, 5, 3, 1};
        Observable observbale = Observable.just(items1, items2);
        observbale.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());
            }

            @Override
            public void onNext(Integer[] o) {
                for (int i = 0; i < o.length; i++) {
                    Log.i(TAG, "is result-->" + o[i]);
                }
            }
        });
    }

    /**
     * 指定输出数据范围
     */
    public static void range() {
        Observable observable = Observable.range(23, 4);//23,24,25,26
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("adu", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("adu", "onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("adu", "onNext==》》" + integer);
            }
        });
    }

    /**
     * 过滤某些条件,需要过滤一遍之后再发给观察者
     */
    public static void filter() {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("adu", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("adu", "onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("adu", "inNext==》》" + integer);
            }
        });

    }

}
