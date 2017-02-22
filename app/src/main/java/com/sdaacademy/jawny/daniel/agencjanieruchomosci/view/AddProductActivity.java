package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends AppCompatActivity {

    @BindView(R.id.product_name)
    EditText mProductName;

    @BindView(R.id.product_price)
    EditText mProductPrice;

    @BindView(R.id.choose_date)
    TextView mDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.choose_date)
    public void onChooseDateClick(View view) {

    }

    @OnClick(R.id.add_button)
    public void onAddClick(View view) {

        onBackPressed();
    }
}