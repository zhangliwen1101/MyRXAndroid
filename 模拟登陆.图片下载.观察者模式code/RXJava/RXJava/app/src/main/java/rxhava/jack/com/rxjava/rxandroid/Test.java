package rxhava.jack.com.rxjava.rxandroid;

/**
 * Created by zhangliwen on 2017/10/10.
 */

public class Test {
    public static void main(String[] args) {
        watched xiaoming = new ConcreteWatched();

        watcher watcher1 = new ConreateWacher();
        watcher watcher2 = new ConreateWacher();
        watcher watcher3 = new ConreateWacher();

        //给被观察者添加三个观察者
        xiaoming.addwatcher(watcher1);
        xiaoming.addwatcher(watcher2);
        xiaoming.addwatcher(watcher3);

        xiaoming.notifywatcher("我被你们观察到了");
    }
}
