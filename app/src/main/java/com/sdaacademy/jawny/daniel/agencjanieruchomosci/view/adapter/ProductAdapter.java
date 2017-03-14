package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget.ProductCardView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> mProducts;

    public ProductAdapter(List<Product> products) {
        mProducts = products;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        Product product = getItem(position);
        holder.mProductCardView.bindTo(product, (ProductCardView.ProductCardViewInterface) this);
    }

    public void swapData(final List<Product> data) {
        if (data != null) {
            mProducts.clear();
            mProducts.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public Product getItem(int position) {
        return mProducts.get(position);
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        ProductCardView mProductCardView;

        public ProductHolder(View itemView) {
            super(itemView);
            mProductCardView = (ProductCardView) itemView.findViewById(R.id.product);
        }
    }
}
