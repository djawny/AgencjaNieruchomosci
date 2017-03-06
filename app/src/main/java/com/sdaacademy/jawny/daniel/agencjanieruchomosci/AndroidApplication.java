package com.sdaacademy.jawny.daniel.agencjanieruchomosci;

import android.app.Application;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.database.Database;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.database.DatabaseImpl;

public class AndroidApplication extends Application {

    private static Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = new DatabaseImpl(this);
        ((DatabaseImpl) mDatabase).getWritableDatabase();
    }

    public static Database getmDatabase() {
        return mDatabase;
    }
}
