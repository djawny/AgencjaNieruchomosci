package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.adapter.ProductAdapter;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui.MainActivity.ADD_PRODUCT_REQUEST_CODE;

public class FragmentProductsList extends Fragment implements ProductAdapter.OnProductClickedListener, ProductListView {

    private static final String TAG = FragmentProductsList.class.getSimpleName();

    @BindView(R.id.products_recycle_view)
    RecyclerView mRecycleView;

    @BindView(R.id.status_info)
    TextView mStatusInfo;

    public interface OnProductSelectedListener {

        void onProductSelected(Product product);
        void onProductReady(List<Product> products);

    }
    private OnProductSelectedListener mListener;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    private ProductAdapter mProductAdapter;
    private CompositeDisposable mCompositeDisposable;
    private ProgressDialog mProgressDialog;
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
        configureDisposable();
        setProgressBar(getActivity());
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
                mProgressDialog.show();
                mCompositeDisposable.add(mProductRepository
                        .getProductsStream()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::displayProducts, this::handleProductRepositoryError));
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
        disposeDisposable();
        dismissProgressDialog();
    }

    @Override
    public void showProducts() {

    }

    @Override
    public void showErrorInfo() {

    }

    @Override
    public void showNoDataInfo() {

    }

    private void dismissProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void configureDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    private void disposeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    private void setRecycleView() {
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mProgressDialog.show();
        mCompositeDisposable.add(mProductRepository
                .getProductsStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayProducts, this::handleProductRepositoryError));
    }

    private void displayProducts(List<Product> products) {
        mListener.onProductReady(products);
        if (mProductAdapter == null) {
            mProductAdapter = new ProductAdapter(getActivity(), products, this);
            mRecycleView.setAdapter(mProductAdapter);
        } else {
            mProductAdapter.swapData(products);
        }
        dismissProgressDialog();
    }

    private void handleProductRepositoryError(Throwable error) {
        Log.d(TAG, error.getLocalizedMessage(), error);
        dismissProgressDialog();
    }

    private void setProgressBar(Activity activity) {
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setTitle("Proszę czekać...");
        mProgressDialog.setMessage("Wczytywanie danych...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }
}
