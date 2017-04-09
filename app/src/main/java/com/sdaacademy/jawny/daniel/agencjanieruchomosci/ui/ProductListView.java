package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.base.BaseView;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.util.List;

interface ProductListView extends BaseView {

    void showProducts(List<Product> products);

    void showErrorInfo(Throwable error);

    void showNoDataInfo();
}
