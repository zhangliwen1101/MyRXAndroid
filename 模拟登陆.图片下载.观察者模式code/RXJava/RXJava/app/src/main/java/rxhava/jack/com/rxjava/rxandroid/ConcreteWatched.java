package rxhava.jack.com.rxjava.rxandroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangliwen on 2017/10/10.
 */

public class ConcreteWatched implements watched {
    private List<watcher> list = new ArrayList<>();

    @Override
    public void addwatcher(watcher watcher) {
        list.add(watcher);
    }

    @Override
    public void removewatcher(watcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notifywatcher(String str) {
        for (watcher watcher : list){
            watcher.updata(str);
        }
    }
}
