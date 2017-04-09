package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.base.BaseView;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

interface ProductDetailsView extends BaseView{

    void showProductDetails(Product product);

    void showErrorInfo(Throwable error);
}
