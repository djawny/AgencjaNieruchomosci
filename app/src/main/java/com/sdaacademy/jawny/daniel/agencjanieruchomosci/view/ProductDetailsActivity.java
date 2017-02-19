package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    public static final String INTENT_PRODUCT_ID = ProductDetailsActivity.class.getSimpleName() + "productId";

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        int productId = bundle.getInt(INTENT_PRODUCT_ID);
        Product product = mProductRepository.getProduct(productId);
        setDisplay(product);
        setToolBar(product);
    }

    private void setDisplay(Product product) {
        mProductImage.setImageResource(product.getmImageResId());
    }

    private void setToolBar(Product product) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(product.getmName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
