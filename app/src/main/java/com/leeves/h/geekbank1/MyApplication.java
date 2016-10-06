package com.leeves.h.geekbank1;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Function：
 * Created by h on 2016/9/17.
 *
 * @author
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
