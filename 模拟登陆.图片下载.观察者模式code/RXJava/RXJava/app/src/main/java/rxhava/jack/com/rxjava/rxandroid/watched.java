package rxhava.jack.com.rxjava.rxandroid;

/**
 * Created by zhangliwen on 2017/10/10.
 */

public interface watched {
    public void addwatcher(watcher watcher);

    public void removewatcher(watcher watcher);

    public void notifywatcher(String str);

}
