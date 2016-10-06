package com.leeves.h.geekbank1.xml;

import android.text.TextUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Function：
 * Created by h on 2016/8/22.
 *
 * @author Leeves
 */
public class SAXParseHandler extends DefaultHandler {

    public static final String ITEM = "item";
    List<WebURL> mWebURLs ;
    private WebURL mWebURL;
    boolean mIsItem;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        mWebURLs = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        mWebURL = new WebURL();
        if (TextUtils.equals(localName,ITEM)){
            for (int i = 0; i < attributes.getLength(); i++) {
                if (TextUtils.equals(attributes.getLocalName(i),"id")){
                    mWebURL.setID(Integer.valueOf(attributes.getValue(i)));
                }
            }
            mIsItem = true;
        }
        mIsItem = false;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (TextUtils.equals(localName,ITEM)){
            mWebURLs.add(mWebURL);
        }
    }

    //读取里面的数据，例如test.xml中百度，淘宝
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String content = String.valueOf(ch,start,length);
        if (mIsItem){
            mWebURL.setContent(content);
            mIsItem = false;
        }
    }

    public List<WebURL> getXMLList() {
            return mWebURLs;
    }
}
