package com.sdaacademy.jawny.daniel.agencjanieruchomosci.base;

public abstract class BasePresenter<V extends BaseView> {

    private V mView;

    public V getmView() {
        return mView;
    }

    public void setmView(V view) {
        if (view == null) {
            throw new IllegalArgumentException("Null view in Presenter");
        }
        mView = view;
    }
}
