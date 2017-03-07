package com.sdaacademy.jawny.daniel.agencjanieruchomosci.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = Product.TABLE_NAME)
public class Product implements Serializable{

    static final String TABLE_NAME = "products";

    @DatabaseField(columnName = "id", generatedId = true)
    private int mId;

    @DatabaseField(columnName = "name", canBeNull = false, unique = true)
    private String mName;

    @DatabaseField(columnName = "price", canBeNull = false)
    private int mPrice;

    @DatabaseField(columnName = "imageName", defaultValue = "d3")
    private String mImageName;

    public Product() {
    }

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
