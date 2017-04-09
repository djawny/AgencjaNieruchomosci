package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.base.BasePresenter;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.util.Precondition;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

public class ProductListPresenter extends BasePresenter<ProductListView> {

    private ProductRepositoryInterface mProductRepository;

    public ProductListPresenter(ProductRepositoryInterface productRepository, Scheduler subscriber, Scheduler observer) {
        super(subscriber, observer);
        mProductRepository = Precondition.checkNotNull(productRepository);
    }

    public void loadProducts() {
        addDisposable(mProductRepository
                .getProductsStream()
                .subscribeOn(mSubscribeScheduler)
                .observeOn(mObserveScheduler)
                .subscribeWith(new DisposableObserver<List<Product>>() {
                    @Override
                    public void onNext(List<Product> products) {
                        if (!products.isEmpty()) {
                            getView().showProducts(products);
                        } else {
                            getView().showNoDataInfo();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showErrorInfo(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }
}
