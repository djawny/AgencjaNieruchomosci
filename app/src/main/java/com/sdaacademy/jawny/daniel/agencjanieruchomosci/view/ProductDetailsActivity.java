package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);



//        setupToolbar();
//        displayData();
    }

}
