package com.sdaacademy.jawny.daniel.agencjanieruchomosci.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.sql.SQLException;
import java.util.List;

public class DatabaseOrmImpl extends OrmLiteSqliteOpenHelper implements Database {

    private final static String DB_NAME = "databaseOrmLite.db";
    private final static int DB_VERSION = 1;

    private RuntimeExceptionDao<Product, Integer> mProductDao;

    public DatabaseOrmImpl(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        mProductDao = getRuntimeExceptionDao(Product.class);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Product.class);
        } catch (SQLException e) {
            Log.e(DB_NAME, "Error onCreate", e);
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Product.class, true);
        } catch (SQLException e) {
            Log.e(DB_NAME, "Error onUpgrade", e);
            e.printStackTrace();
        }
    }

    @Override
    public void saveProducts(List<Product> products) {
        for (Product product : products) {
            mProductDao.createIfNotExists(product);
        }
    }

    @Override
    public List<Product> getProducts() {
        return mProductDao.queryForAll();
    }

    @Override
    public Product getProduct(int productId) {
        return mProductDao.queryForId(productId);
//        try {
//            QueryBuilder<Product, Integer> query = mProductDao.queryBuilder();
//            return query
//                    .where()
//                    .eq("id", productId)
//                    .queryForFirst();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    @Override
    public void saveProduct(String name, int price) {
        mProductDao.createIfNotExists(new Product(0, name, price, null));
    }
}
