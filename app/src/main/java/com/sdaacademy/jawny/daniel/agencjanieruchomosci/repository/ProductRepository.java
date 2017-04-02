package com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.AndroidApplication;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.database.Database;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class ProductRepository implements ProductRepositoryInterface {

    private static ProductRepository mInstance = new ProductRepository();

    private final Database mDatabase;

    private ProductRepository() {

        mDatabase = AndroidApplication.getmDatabase();

        Product product1 = new Product(1, "dom 1", 1000, "d1");
        Product product2 = new Product(2, "dom 2", 2000, "d2");
        Product product3 = new Product(3, "dom 3", 3000, "d3");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

//        for (int i = 0; i < 1000; i++) {
//            Product product = new Product(i, "dom" + String.valueOf(i), i, "d1");
//            products.add(product);
//        }

        mDatabase.saveProducts(products);
    }

    public static ProductRepositoryInterface getInstance() {
        return mInstance;
    }

    @Override
    public Observable<List<Product>> rxGetProducts() {
        return Observable.defer(() -> {
            Thread.sleep(2000);
            return Observable.just(mDatabase.getProducts());
        });
    }

    @Override
    public Observable<Product> rxGetProduct(int id) {
        return Observable.defer(() -> Observable.just(mDatabase.getProduct(id)));
    }

    @Override
    public void addProduct(String name, int price) {
        mDatabase.saveProduct(name, price);
    }
}
