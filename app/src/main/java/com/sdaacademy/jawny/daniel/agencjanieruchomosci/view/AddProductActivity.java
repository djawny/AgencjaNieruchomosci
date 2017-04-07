package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddProductActivity extends AppCompatActivity {

    @BindView(R.id.product_name)
    EditText mProductName;

    @BindView(R.id.product_price)
    EditText mProductPrice;

    private CompositeDisposable mCompositeDisposable;


    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposeDisposable();
    }

    @OnClick(R.id.add_button)
    public void onAddClicked(View view) {
        String name = mProductName.getText().toString().trim();
        String price = mProductPrice.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(price)) {
            mProductRepository
                    .addProductStream(name, Integer.parseInt(price))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<Void>() {
                        @Override
                        public void onNext(Void value) {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(AddProductActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                            Intent returnIntent = getIntent();
                            setResult(RESULT_OK, returnIntent);
                            onBackPressed();
                        }
                    });
        }
    }

    @OnClick(R.id.chancel_button)
    public void onChancelClicked(View view) {
        onBackPressed();
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
}
