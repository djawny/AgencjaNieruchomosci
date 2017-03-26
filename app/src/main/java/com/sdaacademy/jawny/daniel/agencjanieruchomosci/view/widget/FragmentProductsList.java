package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.ProductDetailsActivity;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.adapter.ProductAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentProductsList extends Fragment implements ProductAdapter.OnProductSelectedListener {

    public static final String INTENT_PRODUCT_ID = ProductDetailsActivity.class.getSimpleName() + "productId";

    @BindView(R.id.products_recycle_view)
    RecyclerView mRecycleView;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();
    private ProductAdapter mProductAdapter;
    private List<Product> mProducts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_products_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRecycleView();
    }

    private void setRecycleView() {
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mProducts = mProductRepository.getProducts();
        mProductAdapter = new ProductAdapter(getActivity(), mProducts, this);
        mRecycleView.setAdapter(mProductAdapter);
    }

    @Override
    public void onProductSelected(Product product) {
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra(INTENT_PRODUCT_ID, product.getmId());
        startActivity(intent);
        Log.d(getClass().getSimpleName(), "Product clicked " + product.getmName());
    }

    @Override
    public void onResume() {
        super.onResume();
        mProducts = mProductRepository.getProducts();
        mProductAdapter.swapData(mProducts);
    }
}
