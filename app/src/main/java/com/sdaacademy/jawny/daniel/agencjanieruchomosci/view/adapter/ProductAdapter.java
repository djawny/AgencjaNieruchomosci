package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget.ProductCardView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> objects) {
        super(context, R.layout.row_layout, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View rowView, @NonNull ViewGroup parent) {
        Product product = getItem(position);

        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
        }
        ProductCardView mProductCardView = (ProductCardView) rowView.findViewById(R.id.product);
        mProductCardView.bindTo(product, (ProductCardView.ProductCardViewInterface) getContext());

        return rowView;
    }
}
