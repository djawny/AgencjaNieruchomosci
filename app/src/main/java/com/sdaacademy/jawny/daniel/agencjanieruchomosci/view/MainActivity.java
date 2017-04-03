package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget.FragmentProductDetails;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget.FragmentProductsList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements FragmentProductsList.OnProductSelectedListener {

    public static final String PRODUCT_ID = ProductDetailsActivity.class.getSimpleName() + "productId";
    public static final int ADD_PRODUCT_REQUEST_CODE = 1;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    FragmentProductsList mFragmentProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolBar();
        mFragmentProductList = (FragmentProductsList) getSupportFragmentManager().findFragmentById(R.id.fragment_products_list);
        Log.d("tag", String.valueOf(getFragmentProductDetails()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                android.os.Process.killProcess(android.os.Process.myPid());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.add_new_product)
    public void onAddProductClicked(View view) {
        Intent intent = new Intent(this, AddProductActivity.class);
        mFragmentProductList.startActivityForResult(intent, ADD_PRODUCT_REQUEST_CODE);
    }

    @Override
    public void onProductSelected(Product product) {
        FragmentProductDetails fragment = getFragmentProductDetails();
        if (fragment == null) {
            Intent intent = new Intent(this, ProductDetailsActivity.class);
            intent.putExtra(PRODUCT_ID, product.getmId());
            startActivity(intent);
            Log.d(getClass().getSimpleName(), "Product clicked " + product.getmName());
        } else {
            fragment.updateProductDetails(product);
        }
    }

    @Override
    public void onProductReady(List<Product> products) {
        FragmentProductDetails fragmentProductDetails = getFragmentProductDetails();
        if (fragmentProductDetails != null && !products.isEmpty()) {
            fragmentProductDetails.updateProductDetails(products.get(0));
        }
    }

    public FragmentProductDetails getFragmentProductDetails() {
        final FragmentProductDetails fragment = (FragmentProductDetails) getSupportFragmentManager().findFragmentById(R.id.fragment_product_details_land);
        if (fragment != null && !fragment.isAdded()) {
            return null;
        }
        return fragment;
    }
}
