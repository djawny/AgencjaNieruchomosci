package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.base.BasePresenter;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.util.Precondition;

import io.reactivex.Scheduler;

public class ProductDetailsPresenter extends BasePresenter<ProductDetailsView>{

    private ProductRepositoryInterface mProductRepository;

    public ProductDetailsPresenter(ProductRepositoryInterface productRepository,Scheduler subscriber, Scheduler observer) {
        super(subscriber, observer);
        mProductRepository = Precondition.checkNotNull(productRepository);
    }

    public void loadProduct(){

    }
}
