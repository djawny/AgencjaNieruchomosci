package com.sdaacademy.jawny.daniel.agencjanieruchomosci;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.database.Database;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.database.DatabaseOrmImpl;

public class AndroidApplication extends Application {

    private static Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
//        mDatabase = new DatabaseSQLiteImpl(this);
        mDatabase = OpenHelperManager.getHelper(this, DatabaseOrmImpl.class);
    }

    public static Database getmDatabase() {
        return mDatabase;
    }
}
