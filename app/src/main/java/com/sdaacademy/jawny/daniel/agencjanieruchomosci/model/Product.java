package com.sdaacademy.jawny.daniel.agencjanieruchomosci.model;

public class Product {

    private int mId;
    private String mName;
    private int mPrice;
    private String mImageName;

    public Product(int mId, String mName, int mPrice, String mImageName) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImageName = mImageName;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public int getmPrice() {
        return mPrice;
    }

    public String getmImageName() {
        return mImageName;
    }
}
