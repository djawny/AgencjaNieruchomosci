package com.sdaacademy.jawny.daniel.agencjanieruchomosci.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseImpl extends SQLiteOpenHelper implements Database {

    private final static String NAME = "database.db";
    private final static int VERSION = 1;

    private static final String DB_CREATE_TODO_TABLE =
            "CREATE TABLE products(" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "price INTEGER DEFAULT 0," +
                    "imageName TEXT" +
                    ");";


    public DatabaseImpl(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
