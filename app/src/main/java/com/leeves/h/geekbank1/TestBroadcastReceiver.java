package com.leeves.h.geekbank1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Funtion：接收广播
 * Created by h on 2016/7/29.
 */
public class TestBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //receive broadcast, handle data
        // >10s ANR work thread ,不能做耗时操作
        if (intent !=null){
            if (TextUtils.equals(intent.getAction(),SendBroadcastActivity.COM_GEEKBAND_TEST_BROADCAST)){
                    String toastString = intent.getStringExtra("toast");
                Toast.makeText(context,toastString,Toast.LENGTH_LONG).show();
            }
        }
    }
}
