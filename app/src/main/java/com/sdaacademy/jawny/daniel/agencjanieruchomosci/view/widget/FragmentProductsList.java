package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.widget;

import android.content.Context;
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
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.adapter.ProductAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.sdaacademy.jawny.daniel.agencjanieruchomosci.view.MainActivity.ADD_PRODUCT_REQUEST_CODE;

public class FragmentProductsList extends Fragment implements ProductAdapter.OnProductClickedListener {

    private static final String TAG = FragmentProductsList.class.getSimpleName();

    @BindView(R.id.products_recycle_view)
    RecyclerView mRecycleView;

    public interface OnProductSelectedListener {
        void onProductSelected(Product product);
    }

    private OnProductSelectedListener mListener;
    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();
    private ProductAdapter mProductAdapter;
    private DisposableObserver<List<Product>> disposableObserver;

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

    @Override
    public void onProductClicked(Product product) {
        mListener.onProductSelected(product);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRODUCT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                disposableObserver = mProductRepository
                        .rxGetProducts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<Product>>() {
                            @Override
                            public void onNext(List<Product> products) {
                                if (mProductAdapter != null) {
                                    mProductAdapter.swapData(products);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, e.getMessage(), e);
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProductSelectedListener) {
            mListener = (OnProductSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnProductSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposableObserver != null && !disposableObserver.isDisposed()) {
            disposableObserver.dispose();
        }
    }

    private void setRecycleView() {
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        disposableObserver = mProductRepository
                .rxGetProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Product>>() {
                    @Override
                    public void onNext(List<Product> products) {
                        mProductAdapter = new ProductAdapter(getActivity(), products, FragmentProductsList.this);
                        mRecycleView.setAdapter(mProductAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
