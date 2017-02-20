package com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.List;

public interface ProductRepositoryInterface {

    List<Product> getProducts();

    Product getProduct(int id);
}
