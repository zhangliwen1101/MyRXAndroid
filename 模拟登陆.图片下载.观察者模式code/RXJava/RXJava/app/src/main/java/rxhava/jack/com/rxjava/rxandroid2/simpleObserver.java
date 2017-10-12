package rxhava.jack.com.rxjava.rxandroid2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by zhangliwen on 2017/10/10.
 * 创建一个被观察者
 */

public class simpleObserver implements Observer {

    public simpleObserver(simpleObserveble observeble) {
        observeble.addObserver(this);
    }

    //当被观察者状态发生改变的时候会被调用
    @Override
    public void update(Observable observable, Object o) {
        System.out.println("change data is:"+((simpleObserveble)observable).getData());
    }
}
