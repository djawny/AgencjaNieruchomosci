package com.sdaacademy.jawny.daniel.agencjanieruchomosci.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSQLiteImpl extends SQLiteOpenHelper implements Database {

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
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL UNIQUE," +
                    "price INTEGER DEFAULT 0," +
                    "imageName TEXT DEFAULT d3" +
                    ");";

    private static final String DROP_TODO_TABLE =
            "DROP TABLE IF EXISTS products";
    private static final String ADD_COLUMN =
            "ALTER TABLE products"
                    + "ADD test TEXT";

    public DatabaseSQLiteImpl(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TODO_TABLE);
        onCreate(db);
//        db.execSQL(ADD_COLUMN);
    }

    @Override
    public void saveProducts(List<Product> products) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            for (Product product : products) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(NAME, product.getmName());
                contentValues.put(PRICE, product.getmPrice());
                contentValues.put(IMAGE_NAME, product.getmImageName());
                long id = db.insert(PRODUCTS, null, contentValues);
                Log.i(DATABASE, "" + id);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCTS, null, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            int idColumnIndex = cursor.getColumnIndex(ID);
            int id = cursor.getInt(idColumnIndex);

            int nameColumnIndex = cursor.getColumnIndex(NAME);
            String name = cursor.getString(nameColumnIndex);

            int priceColumnIndex = cursor.getColumnIndex(PRICE);
            int price = cursor.getInt(priceColumnIndex);

            int imageNameColumnIndex = cursor.getColumnIndex(IMAGE_NAME);
            String imageName = cursor.getString(imageNameColumnIndex);

            Product product = new Product(id, name, price, imageName);
            products.add(product);

        } while (cursor.moveToNext());
        cursor.close();

        return products;
    }

    @Override
    public Product getProduct(int productId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCTS, null, "id = ?", new String[]{String.valueOf(productId)}
                , null, null, null);
        cursor.moveToFirst();
        Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
        cursor.close();
        return product;
    }

    @Override
    public void saveProduct(String name, int price) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, name);
            contentValues.put(PRICE, price);
            long id = db.insert(PRODUCTS, null, contentValues);
            Log.i(DATABASE, "" + id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}