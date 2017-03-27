package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepository;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.repository.ProductRepositoryInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends AppCompatActivity {

    @BindView(R.id.product_name)
    EditText mProductName;

    @BindView(R.id.product_price)
    EditText mProductPrice;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_button)
    public void onAddClicked(View view) {
        String name = mProductName.getText().toString().trim();
        String price = mProductPrice.getText().toString().trim();
        if (!name.isEmpty() && !price.isEmpty()) {
            mProductRepository.addProduct(name, Integer.parseInt(price));
            Intent returnIntent = getIntent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

    @OnClick(R.id.chancel_button)
    public void onChancelClicked(View view) {
        onBackPressed();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }
}
