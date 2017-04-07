package com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.List;

import io.reactivex.Observable;

public interface ProductRepositoryInterface {

    Observable<List<Product>> getProductsStream();

    Observable<Product> getProductStream(int id);

    Observable<Void> addProductStream(String name, int price);
}
