package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui.MainActivity.PRODUCT_ID;

public class FragmentProductDetails extends Fragment {

    private static final String TAG = FragmentProductDetails.class.getSimpleName();

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.product_price)
    TextView mProductPrice;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();
    private CompositeDisposable mCompositeDisposable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureDisposable();
        Bundle bundle = getActivity().getIntent().getExtras();
        int productId = bundle != null ? bundle.getInt(PRODUCT_ID, Product.UNDEFINED) : Product.UNDEFINED;
        if (productId != Product.UNDEFINED) {
            mCompositeDisposable.add(mProductRepository
                    .getProductStream(productId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::displayProductDetails, this::handleProductRepositoryError));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposeDisposable();
    }

    private void disposeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    private void configureDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    private void displayProductDetails(Product product) {
        bindViews(product);
        setToolbar(product);
    }

    private void handleProductRepositoryError(Throwable error) {
        Log.d(TAG, error.getLocalizedMessage(), error);
    }

    private void bindViews(Product product) {
        int drawableResourceId = this.getResources().getIdentifier(product.getmImageName(), "drawable", getActivity().getPackageName());
        mProductImage.setImageResource(drawableResourceId);
        mProductPrice.setText(String.valueOf(product.getmPrice()));
    }

    private void setToolbar(Product product) {
        mToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(product.getmName());
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
    }

    public void update(Product product) {
        displayProductDetails(product);
        updateToolbar();
    }

    private void updateToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
