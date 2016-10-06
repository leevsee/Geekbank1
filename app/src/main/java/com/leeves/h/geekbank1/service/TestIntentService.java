package com.leeves.h.geekbank1.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by h on 2016/7/29.
 */
public class TestIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TestIntentService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // do more works UI线程 大于 10秒 --》ANR （无响应）

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //排队  像MessageQueue 同步操作：排队领书，处理Intent数据
    }
}
