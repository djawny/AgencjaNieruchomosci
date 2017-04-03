package com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.List;

import io.reactivex.Observable;

public interface ProductRepositoryInterface {

    Observable<List<Product>> getProductsObservable();

    Observable<Product> getProductObservable(int id);

    void addProduct(String name, int price);
}
