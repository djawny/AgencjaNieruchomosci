package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget.ProductCardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> mProducts;

    public ProductAdapter() {
        mProducts = new ArrayList<>();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = getItem(position);
        holder.mProductCardView.bindTo(product, (ProductCardView.ProductCardViewInterface) holder.mProductCardView.getContext());
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

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_card_view)
        ProductCardView mProductCardView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
