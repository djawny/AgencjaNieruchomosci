package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentTestActivity extends AppCompatActivity {

    private static final String TAG = Fragment1.class.getCanonicalName();

    Fragment1 fragment1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        ButterKnife.bind(this);

//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.activity_fragment_test, Fragment1.getInstance("Daniel"), TAG)
//                .commitNow();

        fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment_1);
//        fragment1 = (Fragment1) getSupportFragmentManager().findFragmentByTag(TAG);

    }

    @OnClick(R.id.duplicate)
    public void onDuplicateButtonClicked() {
        fragment1.duplicate();
    }
}
