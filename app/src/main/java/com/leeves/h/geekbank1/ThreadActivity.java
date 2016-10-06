package com.leeves.h.geekbank1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

/**
 * Function：
 * Created by h on 2016/8/29.
 *
 * @author Leeves
 */
public class ThreadActivity extends Activity implements View.OnClickListener {

    public static final String GEEK_BANK = "GeekBank";
    private TextView mTextView;
    private Button mDownloadButton;
    private ProgressBar mProgressBar;
    private static final String APK_URL = "http://www.xiangshengw.com/uploads/151119/1-151119160552D0.jpg";
//    private static final String APK_URL = "http://www.xiaopi.com/down-1-18152-1.html";
    public static final String TAG = ThreadActivity.class.getSimpleName();
    private Handler mHandler = new DownloadHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mTextView = (TextView) findViewById(R.id.thread_textView);
        //apk url:http://imtt.dd.qq.com/16891/2A76B7A9A8E841F0D8C1E74AD65FCB3F.apk?fsname=com.tencent.mobileqq_6.5.3_398.apk
        mDownloadButton = (Button) findViewById(R.id.download_button);
        mProgressBar = (ProgressBar) findViewById(R.id.download_progressBar);

        mDownloadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.download_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        download(APK_URL);
                    }
                }).start();
                break;

        }
    }

    private void download(String apkUrl) {
        try {
            URL url = new URL(apkUrl);
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            int contentLength = urlConnection.getContentLength(); //要下载文件的大小

            Log.i(TAG,"the download file's content lenght: "+contentLength);

            String downloadFoldersName = Environment.getExternalStorageDirectory() + File.separator + GEEK_BANK + File.separator;
            //在AndroidManifest中加入写入权限
            File file = new File(downloadFoldersName);
            if (!file.exists()){
                file.mkdir();//返回是boolean值
            }

            ///storage/emulated/0/GeekBank/
            String fileName = downloadFoldersName + "1-151119160552D0.jpg";
            File apkFile = new File(fileName);
            if (apkFile.exists()){
                apkFile.delete();
            }
            int downloadSize = 0;

            byte[] bytes = new byte[1024];
            int length = 0;

            OutputStream outputStream = new FileOutputStream(fileName);

            while((length = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,length);
                downloadSize += length;
                int progress = downloadSize * 100 / contentLength;
                //已经对主线程，子线程做了处理，用post
                Message message = mHandler.obtainMessage();
                message.what = 0;
                message.obj = progress;
                mHandler.sendMessage(message);
//                mProgressBar.setProgress(progress);
//                Log.i(TAG,"download progress" + progress);
            }
            Log.i(TAG,"download success");
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG,"download failure");
        }
    }

    public static class DownloadHandler extends Handler{
        //弱引用
        public final WeakReference<ThreadActivity> mThreadActivityWeakReference ;
        //弱引用的初始化
        public DownloadHandler(ThreadActivity threadActivityWeakReference) {
            mThreadActivityWeakReference =new WeakReference<>(threadActivityWeakReference);
        }
//        public DownloadHandler(WeakReference<ThreadActivity> threadActivityWeakReference) {
//            mThreadActivityWeakReference = threadActivityWeakReference;
//        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ThreadActivity activity = mThreadActivityWeakReference.get();//弱引用获得对象
            switch (msg.what){
                case 0:
                    int progress = (int) msg.obj;
                    activity.getProgressBar().setProgress(progress);
                    activity.getTextView().setText("---------------progress:"+progress);
                    if (progress == 100){
                        Toast.makeText(activity,"download success",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    public TextView getTextView() {
        return mTextView;
    }
    public ProgressBar getProgressBar() {
        return mProgressBar;
    }
}
