package com.leeves.h.geekbank1.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Function：
 * Created by h on 2016/9/6.
 *
 * @author
 */
public class Person implements Parcelable {

    String mName;
    int mAge;
    String mAcatarUrl;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getAcatarUrl() {
        return mAcatarUrl;
    }

    public void setAcatarUrl(String acatarUrl) {
        mAcatarUrl = acatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeInt(this.mAge);
        dest.writeString(this.mAcatarUrl);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.mName = in.readString();
        this.mAge = in.readInt();
        this.mAcatarUrl = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
