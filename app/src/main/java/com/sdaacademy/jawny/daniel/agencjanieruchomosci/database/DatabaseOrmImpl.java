package com.sdaacademy.jawny.daniel.agencjanieruchomosci.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.List;

public class DatabaseOrmImpl extends OrmLiteSqliteOpenHelper implements Database {

    private final static String DB_NAME = "databaseOrm";
    private final static int DB_VERSION = 1;

    public DatabaseOrmImpl(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void saveProducts(List<Product> products) {

    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(int productId) {
        return null;
    }

    @Override
    public void saveProduct(String name, int price) {

    }
}
