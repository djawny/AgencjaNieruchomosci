package com.sdaacademy.jawny.daniel.agencjanieruchomosci.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui.widget.ProductCardView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends BaseAdapter<Product> {

    public interface OnProductClickedListener {
        void onProductClicked(Product product);
    }

    private OnProductClickedListener mListener;

    public ProductAdapter(Context context, List<Product> products, OnProductClickedListener onProductClickedListener) {
        super(context, products);
        mListener = onProductClickedListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = getLayoutInflater().inflate(R.layout.row_layout, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, final Product product, int position) {
        ((ProductViewHolder) holder).bind(product);
        if (mListener != null) {
            holder.itemView.setOnClickListener(v -> mListener.onProductClicked(product));
        }
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_card_view)
        ProductCardView mProductCardView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Product product) {
            mProductCardView.bindTo(product);
        }
    }
}
