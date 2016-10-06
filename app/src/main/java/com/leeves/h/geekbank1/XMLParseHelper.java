package com.leeves.h.geekbank1;

import android.content.res.XmlResourceParser;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.leeves.h.geekbank1.xml.SAXParseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Function：
 * Created by h on 2016/8/27.
 *
 * @author
 */
public class XMLParseHelper {

    private MainActivity mMainActivity;

    public XMLParseHelper(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    private void testSAXParese() throws ParserConfigurationException, SAXException, IOException {
//        //在SAXParserFactory中获得SAXParser，在SAXParser中获得XMLReader
//        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
//        SAXParser saxParser = saxParserFactory.newSAXParser();
//        XMLReader xmlReader = saxParser.getXMLReader();
//        //在XMLReader中，设置Hadnler
//        SAXParseHandler saxParseHandler = new SAXParseHandler();
//        xmlReader.setContentHandler(saxParseHandler);
//        //调用XMLReader的parser方法，里面参数是inputSource
//        InputStream inputStream = getResources().openRawResource(R.raw.test);
//        InputSource inputSource = new InputSource(inputStream);
//        xmlReader.parse(inputSource);
//        saxParseHandler.getXMLList();

        //简单写法
        XMLReader xmlReaderTest = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        xmlReaderTest.setContentHandler(new SAXParseHandler());
        xmlReaderTest.parse(new InputSource(mMainActivity.getResources().openRawResource(R.raw.test)));
        //pull 必须在res目录下，创建一个xml文件夹，放置xml文件，才可以读取
        XmlResourceParser xmlResourceParser = mMainActivity.getResources().getXml(R.xml.test);
        try {
            while (xmlResourceParser.getEventType() != xmlResourceParser.END_DOCUMENT) {
                if (xmlResourceParser.getEventType() == xmlResourceParser.START_DOCUMENT) {
                    String tagName = xmlResourceParser.getName();
                    if (TextUtils.equals(tagName, "item")) {
                        String id = xmlResourceParser.getAttributeValue(null, "id");
                        String id1 = xmlResourceParser.getAttributeValue(0);
                    }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        //DOM 自己看


        //JSON
        InputStream is = mMainActivity.getResources().openRawResource(R.raw.json);
//        String jsonString = mMainActivity.getStringByInputStream(is);
        String jsonString = getStringByInputStream(is);

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            jsonObject.getString("title");//获取title的值

            JSONObject userJSONObject = jsonObject.getJSONObject("user");
            userJSONObject.getLong("id");
            JSONArray jsonArray = jsonObject.getJSONArray("images");
            jsonArray.get(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Gson
        Gson gson = new Gson();
        UserData userData = gson.fromJson(jsonString, UserData.class);

    }

    //转换为json
    private String getStringByInputStream(InputStream is) {
        return null;
    }
}
