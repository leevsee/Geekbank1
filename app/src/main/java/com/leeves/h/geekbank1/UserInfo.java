package com.leeves.h.geekbank1;

import java.io.Serializable;

/**
 * Created by h on 2016/6/28.
 */
public class UserInfo implements Serializable{
    private String mUserName;
    private int mAge;
    private String mAvatarUrl;
    private float mWeight;

    public UserInfo(String UserName, int Age) {
        mUserName = UserName;
        mAge = Age;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setmUserName(String UserName) {
        mUserName = UserName;
    }

    public int getAge() {
        return mAge;
    }

    public void setmAge(int Age) {
        mAge = Age;
    }
}
