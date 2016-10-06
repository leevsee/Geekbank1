package com.leeves.h.geekbank1.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * Function：
 * Created by h on 2016/9/2.
 *
 * @author Leeves
 */
public class MessengerService extends Service {

    //适合于多进程，单线程，需要考虑线程安全
    Messenger mMessenger = new Messenger(new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();//AIDL基础来实现的
    }

    class IncomingHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    //TODO
                    break;
            }
        }
    }
}
