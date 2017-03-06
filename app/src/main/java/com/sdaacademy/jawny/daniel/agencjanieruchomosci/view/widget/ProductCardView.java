package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductCardView extends CardView {

    public interface ProductCardViewInterface {
        void onProductClicked(Product product);
    }

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.product_name)
    TextView mProductName;

    @BindView(R.id.product_price)
    TextView mProductPrice;

    public ProductCardView(Context context) {
        super(context);
        init();
    }

    public ProductCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProductCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_product_card_view, this);
        ButterKnife.bind(this);
    }

    public void bindTo(final Product product, final ProductCardViewInterface productCardViewInterface) {
        mProductName.setText(product.getmName());
        mProductPrice.setText(String.valueOf(product.getmPrice()));
        int drawableResourceId = this.getResources().getIdentifier(product.getmImage(), "drawable", getContext().getPackageName());
        mProductImage.setImageResource(drawableResourceId);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                productCardViewInterface.onProductClicked(product);
            }
        });
    }
}
