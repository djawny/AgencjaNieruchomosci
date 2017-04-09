package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.base.BasePresenter;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.util.Precondition;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

public class AddProductPresenter extends BasePresenter<AddProductView> {

    private ProductRepositoryInterface mProductRepository;

    public AddProductPresenter(ProductRepositoryInterface productRepository, Scheduler subscriber, Scheduler observer) {
        super(subscriber, observer);
        mProductRepository = Precondition.checkNotNull(productRepository);
    }

    public void addProduct(String name, String price) {
        int parsedPrice = Integer.parseInt(price);
        addDisposable(mProductRepository
                .addProductStream(name, parsedPrice)
                .subscribeOn(mSubscribeScheduler)
                .observeOn(mObserveScheduler)
                .subscribeWith(new DisposableObserver<Void>() {
                    @Override
                    public void onNext(Void value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showErrorInfo(e);
                    }

                    @Override
                    public void onComplete() {
                        getView().closeWindow();
                    }
                })
        );
    }
}
