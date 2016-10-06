package com.leeves.h.geekbank1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Function：
 * Created by h on 2016/8/25.
 *
 * @author
 */
public class UserData {

    //自动帮你替换title
    @SerializedName("title")
    private String mTitle;

    @SerializedName("content")
    private String mContent;

    @SerializedName("user")
    private User mUser;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    //
    @SerializedName("images")
    private List<String> mImages;

//    //如果里面是列表的话，定义一个内部类
//    public class ImageItem{
//        @SerializedName("user")
//        private User mUser;
//    }

    public class User{

        @SerializedName("id")
        private Long mId;

        @SerializedName("name")
        private Long mName;

        @SerializedName("avatar")
        private Long mAvatar;

        public Long getId() {
            return mId;
        }

        public void setId(Long id) {
            mId = id;
        }

        public Long getName() {
            return mName;
        }

        public void setName(Long name) {
            mName = name;
        }

        public Long getAvatar() {
            return mAvatar;
        }

        public void setAvatar(Long avatar) {
            mAvatar = avatar;
        }
    }

}
