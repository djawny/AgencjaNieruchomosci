package com.sdaacademy.jawny.daniel.agencjanieruchomosci.model;

public class Product {

    private int mId;
    private String mName;
    private int mPrice;
    private String mImage;

    public Product(int mId, String mName, int mPrice, String mImage) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
        this.mImage = mImage;
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

    public String getmImage() {
        return mImage;
    }
}
