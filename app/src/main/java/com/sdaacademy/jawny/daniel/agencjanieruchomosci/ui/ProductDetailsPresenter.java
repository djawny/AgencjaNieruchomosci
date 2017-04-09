package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.base.BasePresenter;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.util.Precondition;

import io.reactivex.Scheduler;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.DisposableObserver;

public class ProductDetailsPresenter extends BasePresenter<ProductDetailsView> {

    private ProductRepositoryInterface mProductRepository;

    public ProductDetailsPresenter(ProductRepositoryInterface productRepository, Scheduler subscriber, Scheduler observer) {
        super(subscriber, observer);
        mProductRepository = Precondition.checkNotNull(productRepository);
    }

    public void loadProduct(int productId) {
        addDisposable(mProductRepository
                .getProductStream(productId)
                .subscribeOn(mSubscribeScheduler)
                .observeOn(mObserveScheduler)
                .subscribeWith(new DisposableObserver<Product>() {
                    @Override
                    public void onNext(Product product) {
                        getView().showProductDetails(product);
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
