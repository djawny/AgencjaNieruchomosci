package com.sdaacademy.jawny.daniel.agencjanieruchomosci.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddProductActivity extends AppCompatActivity implements AddProductView {

    @BindView(R.id.product_name)
    EditText mProductName;

    @BindView(R.id.product_price)
    EditText mProductPrice;

    private AddProductPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        mPresenter = new AddProductPresenter(ProductRepository.getInstance(), Schedulers.io(), AndroidSchedulers.mainThread());
        mPresenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clearDisposable();
    }

    @OnClick(R.id.add_button)
    public void onAddClicked(View view) {
        String name = mProductName.getText().toString().trim();
        String price = mProductPrice.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(price)) {
            mPresenter.addProduct(name, price);
        }
    }

    @OnClick(R.id.chancel_button)
    public void onChancelClicked(View view) {
        onBackPressed();
    }

    @Override
    public void closeWindow() {
        Intent returnIntent = getIntent();
        setResult(RESULT_OK, returnIntent);
        Toast.makeText(AddProductActivity.this, R.string.product_added, Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    @Override
    public void showErrorInfo(Throwable error) {
        Toast.makeText(AddProductActivity.this, R.string.error, Toast.LENGTH_LONG).show();
    }
}
