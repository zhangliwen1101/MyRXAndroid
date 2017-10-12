package rxhava.jack.com.rxjava.rxandroid2;

/**
 * Created by zhangliwen on 2017/10/10.
 */

public class MyTest {
    public static void main(String[] args) {
        simpleObserveble simpleObserveble =  new simpleObserveble();
        simpleObserver simpleObserver =  new simpleObserver(simpleObserveble);

        simpleObserveble.setData(1);
        simpleObserveble.setData(2);
        simpleObserveble.setData(2);
        simpleObserveble.setData(3);

    }
}
