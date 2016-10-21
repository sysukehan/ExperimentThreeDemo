package com.study.android.kehan.myapplication.Data;

import java.io.Serializable;

/**
 * Created by kehan on 16-10-5.
 * 联系人类
 */
public class Contacts implements Serializable {

    private String firstLetter;
    private String name;
    private String mobileNumber;
    private String type;
    private String location;
    private int backgroundColor;

    public Contacts(String name, String mobileNumber, String type, String location, int backgroundColor) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.type = type;
        this.location = location;
        this.backgroundColor = backgroundColor;
        firstLetter = name.charAt(0) + "";
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public String getLocation() {
        return location;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
