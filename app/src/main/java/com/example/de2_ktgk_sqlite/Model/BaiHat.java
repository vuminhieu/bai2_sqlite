package com.example.de2_ktgk_sqlite.Model;

import java.io.Serializable;

public class BaiHat implements Serializable {
    private int mid;
    private String mName;
    private String mSinger;
    private String mTime;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSinger() {
        return mSinger;
    }

    public void setmSinger(String mSinger) {
        this.mSinger = mSinger;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public BaiHat() {
    }

    public BaiHat(int mid, String mName, String mSinger, String mTime) {
        this.mid = mid;
        this.mName = mName;
        this.mSinger = mSinger;
        this.mTime = mTime;
    }
    public BaiHat( String mName, String mSinger, String mTime) {
        this.mName = mName;
        this.mSinger = mSinger;
        this.mTime = mTime;
    }
}
