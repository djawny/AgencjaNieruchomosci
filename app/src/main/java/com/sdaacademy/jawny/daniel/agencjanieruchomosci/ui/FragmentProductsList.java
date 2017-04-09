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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.adapter.ProductAdapter;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui.MainActivity.ADD_PRODUCT_REQUEST_CODE;

public class FragmentProductsList extends Fragment implements ProductAdapter.OnProductClickedListener, ProductListView {

    @BindView(R.id.products_recycle_view)
    RecyclerView mRecycleView;

    @BindView(R.id.status_info)
    TextView mStatusInfo;

    public interface OnProductSelectedListener {

        void onProductSelected(Product product);

        void onProductReady(List<Product> products);

    }

    private OnProductSelectedListener mListener;
    private ProductAdapter mProductAdapter;
    private ProgressDialog mProgressDialog;
    private ProductListPresenter mPresenter;

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
        mPresenter = new ProductListPresenter(ProductRepository.getInstance(), Schedulers.io(), AndroidSchedulers.mainThread());
        mPresenter.setView(this);
        setProgressBar(getActivity());
        setRecycleView();
        mProgressDialog.show();
        mPresenter.loadProducts();
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
                mPresenter.loadProducts();
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
        mPresenter.clearDisposible();
    }

    @Override
    public void showProducts(List<Product> products) {
        dismissProgressDialog();
        mRecycleView.setVisibility(View.VISIBLE);
        mStatusInfo.setVisibility(View.GONE);
        mListener.onProductReady(products);
        if (mProductAdapter == null) {
            mProductAdapter = new ProductAdapter(getActivity(), products, this);
            mRecycleView.setAdapter(mProductAdapter);
        } else {
            mProductAdapter.swapData(products);
        }
    }

    @Override
    public void showNoDataInfo() {
        dismissProgressDialog();
        mRecycleView.setVisibility(View.GONE);
        mStatusInfo.setVisibility(View.VISIBLE);
        mStatusInfo.setText(R.string.no_data);
    }

    @Override
    public void showErrorInfo(Throwable error) {
        dismissProgressDialog();
        mRecycleView.setVisibility(View.GONE);
        mStatusInfo.setVisibility(View.VISIBLE);
        mStatusInfo.setText(R.string.error);
    }

    private void setRecycleView() {
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
    }

    private void setProgressBar(Activity activity) {
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setTitle(getString(R.string.please_wait));
        mProgressDialog.setMessage(getString(R.string.loading_data));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void dismissProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
