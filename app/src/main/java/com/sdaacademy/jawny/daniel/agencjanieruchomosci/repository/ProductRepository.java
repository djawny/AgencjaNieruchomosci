package com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.AndroidApplication;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.database.Database;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.ArrayList;
import java.util.List;

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

        mDatabase.saveProducts(products);
    }

    public static ProductRepositoryInterface getInstance() {
        return mInstance;
    }

    @Override
    public List<Product> getProducts() {
        return mDatabase.getProducts();
    }

    @Override
    public Product getProduct(int id) {
        return mDatabase.getProduct(id);
    }

    @Override
    public void addProduct(String name, int price) {
        mDatabase.saveProduct(name, price);
    }
}
