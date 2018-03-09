package com.example.lenovo.myadapter;

import android.app.Application;

/**
 * Created by lenovo on 2018/3/9.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }
}
