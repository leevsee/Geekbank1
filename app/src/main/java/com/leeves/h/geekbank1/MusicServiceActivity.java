package com.leeves.h.geekbank1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.leeves.h.geekbank1.service.MusiceService;

/**
 * Created by h on 2016/7/27.
 */
public class MusicServiceActivity extends Activity implements View.OnClickListener{

    private Button mStarButton;
    private Button mStopButton;
    private MusiceService mMusiceService;
    private static final String TAG = MusicServiceActivity.class.getSimpleName();


    //创建ServiceConnection，监视客户端与服务中间的连接，获得MusiceService中的方法
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusiceService.LocalBinder localBinder = (MusiceService.LocalBinder) service;
            mMusiceService = localBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_musice_service);
        initView();
    }

    private void initView(){
        mStarButton = (Button) findViewById(R.id.star_button);
        mStopButton = (Button) findViewById(R.id.stop_button);

        mStarButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);

        if (mMusiceService != null){
            int progress = mMusiceService.getMusicPlayProgress();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MusicServiceActivity.this,MusiceService.class);
        switch (v.getId()){
            case R.id.star_button:
                startService(intent);
                //因为bingService和Activity继承与Context，所以可以直接使用，绑定MusiceService
                /*Intent service   通过Intent指定要启动的Service
                  ServiceConnection conn    一个ServiceConnection对象
                  int flags     指定绑定时是否自动创建Service（如果还没有创建的话）。0是不自动创建，BIND_AUTO_CREATE自动创建
                 */
                bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.stop_button:
                if (mServiceConnection != null){
                    Log.i(TAG,"传进来了");
                unbindService(mServiceConnection);
                }
                stopService(intent);
                break;
        }
    }
}
