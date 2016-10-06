package com.leeves.h.geekbank1;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Function：
 * Created by h on 2016/9/8.
 *
 * @author
 */
public class Test1 {

    public String requestData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET"); //GET POST
            connection.connect();
            int responseCode =  connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            String result = null;
            Log.i("——————————————————————————","成功");
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
}
