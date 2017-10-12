package rxhava.jack.com.rxjava.downloadimage;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhangliwen on 2017/10/11.
 */

public class DownLoadUtils {
    private OkHttpClient okHttpClient;

    public DownLoadUtils() {
        okHttpClient = new OkHttpClient();
    }

    /**
     * 声明一个观察者对象做为返回结构
     */
    public Observable<byte[]> downLoadImage(String str) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //访问网络操作
                    final Request request = new Request.Builder().url(str).build();
                    //new call
                    Call call = okHttpClient.newCall(request);
                    //请求加入调度
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //处理返回结果
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                if (data != null) {
                                    subscriber.onNext(data);
                                }
                            }
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });
    }
}
