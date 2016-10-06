package com.leeves.h.geekbank1;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Funtion：发送广播
 * Created by h on 2016/7/29.
 */
public class SendBroadcastActivity extends Activity {

    public static final String COM_GEEKBAND_TEST_BROADCAST = "com.geekband.test.broadcast";
    private TestBroadcastReceiver mTestBroadcastReceiver = new TestBroadcastReceiver();
    private Button mSendBroadcastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast);

        //发送广播
        mSendBroadcastButton = (Button) findViewById(R.id.send_broadcast_button);
        mSendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(COM_GEEKBAND_TEST_BROADCAST);
                intent.putExtra("toast","this is my test broadcast");

                sendBroadcast(intent);
//                LocalBroadcastManager  本应用内广播，速度快，性能提高
//                sendOrderedBroadcast();顺序发广播
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(COM_GEEKBAND_TEST_BROADCAST);
        registerReceiver(mTestBroadcastReceiver,intentFilter);//里面是BroadcastReceiver receiver, IntentFilter filter
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
