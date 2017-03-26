package com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.List;

import io.reactivex.Observable;

public interface ProductRepositoryInterface {

    Observable<List<Product>> rxGetProducts();

    Observable<Product> rxGetProduct(int id);

    void addProduct(String name, int price);
}
