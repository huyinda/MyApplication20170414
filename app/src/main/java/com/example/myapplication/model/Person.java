package com.example.myapplication.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Person extends BmobObject{
    String userName;
    String userPsw;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String name) {
        this.userName = name;
    }
    public String getUserPsw() {
        return userPsw;
    }
    public void setUserPsw(String address) {
        this.userPsw = address;
    }

}
