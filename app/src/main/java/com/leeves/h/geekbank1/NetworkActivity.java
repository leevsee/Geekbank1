package com.leeves.h.geekbank1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Function：
 * Created by h on 2016/8/19.
 *
 * @author Leeves
 */
public class NetworkActivity extends Activity implements View.OnClickListener{
    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        findView();
        setListeners();
    }

    private void setListeners() {
        mButton.setOnClickListener(this);
    }

    private void findView() {
        mButton = (Button) findViewById(R.id.get_network_button);
        mEditText = (EditText) findViewById(R.id.network_editText);
        mTextView = (TextView) findViewById(R.id.network_textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_network_button:
                String url = getEditTextUrl();
//                String data = requestData(url);
                new RequestNetworkDataTask().execute(url);
                break;
        }
    }

    private String requestData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET"); //GET POST
            connection.connect();
            int responseCode =  connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            String result = null;
            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                Reader reader = new InputStreamReader(inputStream,"utf-8");//后面的编码用小写
                char[] buff = new char[1024];
                reader.read(buff);
                result = new String(buff);
            }else {
            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //同步代码块（synchronized block） 比较耗cpu
    public synchronized void test(){

    }

    //异步任务处理
    class RequestNetworkDataTask extends AsyncTask<String,Integer,String>{

        //在执行后台耗时操作前被调用。通常用于完成一些初始化的准备工作，比如界面上显示进度条等。
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //主线程
            //UI Loading
        }
        //后台将要完成的任务。可以调用publishProgress()更新任务的执行进度。
        @Override
        protected String doInBackground(String... params) {
            Test1 test = new Test1();
            String result = test.requestData(params[0]);
            return result;
        }
        //在doInBackground()处理完后，系统会自动调用这个方法，将doInBackground()的返回值转给该方法。
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //执行完之后在主线程中
            mTextView.setText(result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
    //获取URL
    public String getEditTextUrl() {
        return mEditText != null ? mEditText.getText().toString() : "";
    }
}
