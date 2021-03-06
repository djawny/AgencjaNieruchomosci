package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductCardView extends CardView {

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.product_name)
    TextView mProductName;

    @BindView(R.id.product_price)
    TextView mProductPrice;

    public ProductCardView(Context context) {
        this(context, null);
    }

    public ProductCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_product_card_view, this);
        ButterKnife.bind(this);
    }

    public void bindTo(Product product) {
        mProductName.setText(product.getmName());
        mProductPrice.setText(String.valueOf(product.getmPrice()));
        int drawableResourceId = this.getResources().getIdentifier(product.getmImageName(), "drawable", getContext().getPackageName());
        mProductImage.setImageResource(drawableResourceId);
    }
}
