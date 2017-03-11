package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

import butterknife.ButterKnife;

public class TestStorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_storage);
        ButterKnife.bind(this);

    }
}
