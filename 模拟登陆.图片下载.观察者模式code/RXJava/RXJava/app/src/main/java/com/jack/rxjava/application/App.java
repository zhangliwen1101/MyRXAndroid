package com.jack.rxjava.application;

import android.app.Application;

/**
 * Created by zhangliwen on 2017/4/7.
 */

public class App extends Application {
    private static App mApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init() {
        mApp = this;
    }


    public static App getApplication() {
        return mApp;
    }
}
