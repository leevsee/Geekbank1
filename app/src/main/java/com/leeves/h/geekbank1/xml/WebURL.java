package com.leeves.h.geekbank1.xml;

/**
 * Function：
 * Created by h on 2016/8/22.
 *
 * @author Leeves
 */
public class WebURL {
    private int ID;
    private String URL;
    private String mContent;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
