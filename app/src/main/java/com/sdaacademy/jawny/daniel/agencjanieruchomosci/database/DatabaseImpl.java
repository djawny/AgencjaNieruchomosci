package com.sdaacademy.jawny.daniel.agencjanieruchomosci.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.List;

public class DatabaseImpl extends SQLiteOpenHelper implements Database {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String IMAGE_NAME = "imageName";
    private static final String PRODUCTS = "products";
    private static final String DATABASE = "database";

    private final static String DB_NAME = "database.db";
    private final static int DB_VERSION = 1;

    private static final String DB_CREATE_TODO_TABLE =
            "CREATE TABLE products(" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "price INTEGER DEFAULT 0," +
                    "imageName TEXT" +
                    ");";

    private static final String DROP_TODO_TABLE =
            "DROP TABLE IF EXISTS products";
    private static final String ADD_COLUMN =
            "ALTER TABLE products"
                    + "ADD test TEXT";

    public DatabaseImpl(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(DROP_TODO_TABLE);
//        onCreate(db);
//        db.execSQL(ADD_COLUMN);
    }

    @Override
    public void saveProducts(List<Product> products) {
        SQLiteDatabase db = getWritableDatabase();
        for (Product product : products) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, product.getmId());
            contentValues.put(NAME, product.getmName());
            contentValues.put(PRICE, product.getmPrice());
            contentValues.put(IMAGE_NAME, product.getmImageName());
            long id = db.insertOrThrow(PRODUCTS, null, contentValues);
            Log.i(DATABASE, "" + id);
        }
    }
}
