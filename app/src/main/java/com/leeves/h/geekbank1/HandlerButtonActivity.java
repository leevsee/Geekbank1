package com.leeves.h.geekbank1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by h on 2016/7/19.
 */
public class HandlerButtonActivity extends Activity {

    public static final int MESSAGE_CODE = 888888;
    private TextView mTextView;
    private TestHandler mTestHandler = new TestHandler(this);

/*    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接受消息
                switch (msg.what){
                case MESSAGE_CODE:
                    int value = (int) msg.obj;
                    mTextView.setText(String.valueOf(value/1000));

                    msg = Message.obtain();
                    msg.arg1 = 0;
                    msg.arg2 = 1;
                    msg.what = MESSAGE_CODE;
                    msg.obj = value - 1000;

                    if (value > 10){
                        sendMessageDelayed(msg,1000);
                    }
                    break;
            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);
        mTextView = (TextView) findViewById(R.id.textView_handle);
        //获得当前消息对象
        Message message = mTestHandler.obtainMessage();

        message.arg1 = 0;//用来存放整型数据的
        message.arg2 = 1;
        message.what = MESSAGE_CODE;//是用来保存消息标识
        message.obj = 10000;//Object类型的任意对象

        //设置延迟
//        mHandler.sendMessageDelayed(message,1000);
        mTestHandler.sendMessageDelayed(message,1000);

/*        mTestHandler.sendEmptyMessageDelayed(); //发送一个空消息，延迟某个动作

        mTestHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },1000);//定时做任务，发消息，延迟发消息*/

    }

    public TextView getTextView() {
        return mTextView;
    }

    public static class TestHandler extends Handler{

        public final WeakReference<HandlerButtonActivity> mHandlerButtonActivityWeakReference;

        public TestHandler(HandlerButtonActivity activity){

            mHandlerButtonActivityWeakReference = new WeakReference<HandlerButtonActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            HandlerButtonActivity handlerButtonActivity = mHandlerButtonActivityWeakReference.get();
            //接受消息
            switch (msg.what){
                case MESSAGE_CODE:
                    int value = (int) msg.obj;
//                    mTextView.setText(String.valueOf(value/1000));
                    handlerButtonActivity.getTextView().setText(String.valueOf(value/1000));//显示倒计时数字

                    msg = Message.obtain();
                    msg.arg1 = 0;
                    msg.arg2 = 1;
                    msg.what = MESSAGE_CODE;
                    msg.obj = value - 1000;

                    if (value > 10){
                        sendMessageDelayed(msg,1000);
                    }
                    break;
            }
        }
    }
}
