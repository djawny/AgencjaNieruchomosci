package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.adapter.ProductAdapter;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget.ProductCardView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ProductCardView.ProductCardViewInterface {

    public static final String INTENT_PRODUCT_ID = ProductDetailsActivity.class.getSimpleName() + "productId";

    @BindView(R.id.products_recycle_view)
    RecyclerView mRecycleView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activity_main)
    View mRootLayout;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();
    private ProductAdapter mProductAdapter;
    private List<Product> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolBar();
        setRecycleView();
    }

    private void setRecycleView() {
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mProducts = mProductRepository.getProducts();
        mProductAdapter = new ProductAdapter(this);
        mRecycleView.setAdapter(mProductAdapter);
        mProductAdapter.swapData(mProducts);
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
        mToolbar.setTitle("Nieruchomości");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProducts = mProductRepository.getProducts();
        mProductAdapter.swapData(mProducts);
    }

    @Override
    public void onProductClicked(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(INTENT_PRODUCT_ID, product.getmId());
        startActivity(intent);
        Log.d(getClass().getSimpleName(), "Product clicked " + product.getmName());
    }

    @OnClick(R.id.add_new_product)
    public void onAddProductClicked(View view) {

        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);

//        Log.d(getClass().getSimpleName(), "New product click");
//
//        Toast.makeText(this, "New product click", Toast.LENGTH_SHORT).show();
//
//        Snackbar.make(mRootLayout,"Brak internetu",Snackbar.LENGTH_INDEFINITE)
//                .setAction("Odśwież", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                })
//                .setActionTextColor(Color.GREEN)
//                .show();
    }
}
