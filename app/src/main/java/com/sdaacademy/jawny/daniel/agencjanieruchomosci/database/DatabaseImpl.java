package com.sdaacademy.jawny.daniel.agencjanieruchomosci.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseImpl extends SQLiteOpenHelper implements Database {

    private final static String NAME = "database.db";
    private final static int VERSION = 1;


    public DatabaseImpl(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
