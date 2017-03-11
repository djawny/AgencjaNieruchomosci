package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestStorageActivity extends AppCompatActivity {

    @BindView(R.id.text1)
    TextView mText1;

    @BindView(R.id.text2)
    TextView mText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_storage);
        ButterKnife.bind(this);

        mText1.setText(getFilesDir().toString());
        mText2.setText(getCacheDir().toString());
    }
}
