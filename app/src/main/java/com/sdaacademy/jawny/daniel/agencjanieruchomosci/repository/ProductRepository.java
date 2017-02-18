package com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository implements ProductRepositoryInterface {

    private static ProductRepository mInstance = new ProductRepository();

    private Map<Integer, Product> mProducts = new HashMap<>();

    private ProductRepository() {
        Product product1 = new Product(1, "dom 1", 1000, R.drawable.d1);
        Product product2 = new Product(2, "dom 2", 2000, R.drawable.d2);
        Product product3 = new Product(3, "dom 3", 3000, R.drawable.d3);

        mProducts.put(1, product1);
        mProducts.put(2, product2);
        mProducts.put(3, product3);
    }

    public static ProductRepositoryInterface getInstance() {
        return mInstance;
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(mProducts.values());
    }
}
