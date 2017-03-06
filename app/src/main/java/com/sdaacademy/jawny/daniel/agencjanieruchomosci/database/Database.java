package com.sdaacademy.jawny.daniel.agencjanieruchomosci.database;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.List;

public interface Database {

    void saveProducts(List<Product> products);

    List<Product> getProducts();
}
