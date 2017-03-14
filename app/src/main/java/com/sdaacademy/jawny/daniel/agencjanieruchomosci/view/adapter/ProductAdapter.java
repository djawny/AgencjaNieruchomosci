package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.adapter;

import android.content.Context;
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
    private LayoutInflater mLayoutInflater;

    public ProductAdapter(Context context, List<Product> products) {
        mLayoutInflater = LayoutInflater.from(context);
        mProducts = new ArrayList<>();
        mProducts.addAll(products);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.row_layout, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = getItem(position);
        holder.mProductCardView.bindTo(product, (ProductCardView.ProductCardViewInterface) holder.mProductCardView.getContext());
    }

    public void swapData(List<Product> products) {
        if (products != null) {
            mProducts.clear();
            mProducts.addAll(products);
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

        public void bind(Product product) {
            ((ProductCardView) itemView).bindTo(product, null);
        }
    }
}
