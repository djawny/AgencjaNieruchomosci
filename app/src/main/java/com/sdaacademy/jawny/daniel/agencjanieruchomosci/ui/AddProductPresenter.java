package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.base.BasePresenter;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.util.Precondition;

import io.reactivex.Scheduler;

public class AddProductPresenter extends BasePresenter<AddProductView> {

    private ProductRepositoryInterface mProductRepository;

    public AddProductPresenter(ProductRepositoryInterface productRepository, Scheduler subscriber, Scheduler observer) {
        super(subscriber, observer);
        mProductRepository = Precondition.checkNotNull(productRepository);
    }
}
