package com.leeves.h.geekbank1.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.leeves.h.geekbank1.MainActivity;
import com.leeves.h.geekbank1.MusicServiceActivity;
import com.leeves.h.geekbank1.R;

/**
 * Created by h on 2016/7/27.
 */
public class MusiceService extends Service {

    private static final String TAG = MusiceService.class.getSimpleName();
    //播放控件
    private MediaPlayer mMediaPlayer;
    private IBinder mIBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"service onCreate");
        //初始化播放控件，找到音乐位置R.raw
        mMediaPlayer = MediaPlayer.create(this, R.raw.pianwei);
//        Notification notification = new Notification(R.drawable.ic_lu,"来通知啦",System.currentTimeMillis());
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this, MusicServiceActivity.class),0);
//        notification.setLatestEventInfo(this,"标题","内容",pendingIntent);
//        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"service onStartCommand");
        //开始播放音乐
        mMediaPlayer.start();
        /*
        START_NOT_STICKY：“非粘性的”。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统不会自动重启该服务。
        START_REDELIVER_INTENT：重传Intent。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统会自动重启该服务，并将Intent的值传入。
        START_STICKY_COMPATIBILITY：START_STICKY的兼容版本，但不保证服务被kill后一定能重启。
         */
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止播放基于List
        mMediaPlayer.stop();
        Log.i(TAG,"service onDestroy");
        stopForeground(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //把本地的Ibinder返回
        return mIBinder;//return到musiceServiceActivity中的mServiceConnection
    }

    public class LocalBinder extends Binder{
        //获取当前的Service，bind是沟通service和activity的桥梁
        public MusiceService getService(){
                return MusiceService.this;
            }
    }

    public int getMusicPlayProgress(){
        return 18;
    }
}
