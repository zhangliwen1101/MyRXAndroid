package rxhava.jack.com.rxjava.rxandroid2;

import java.util.Observable;

/**
 * Created by zhangliwen on 2017/10/10.
 * 创建一个被观察者
 */

public class simpleObserveble extends Observable {
        private  int data =0;

    public int getData() {
        return data;
    }

    public void setData(int i) {
        if (this.data!=i){
            this.data = i;
            setChanged();//发生改变
            notifyObservers();//通知观察者，状态发生改变
        }
    }
}
