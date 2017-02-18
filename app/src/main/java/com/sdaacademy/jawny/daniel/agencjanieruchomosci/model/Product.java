package com.sdaacademy.jawny.daniel.agencjanieruchomosci.model;

public class Product {

    private int mId;
    private String mName;
    private int mPrice;
    private int mImageResId;

    public Product(int mId, String mName, int mPrice, int mImageResId) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImageResId = mImageResId;
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

    public int getmImageResId() {
        return mImageResId;
    }
}
