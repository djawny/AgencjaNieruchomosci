package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

public class FragmentTestActivity extends AppCompatActivity {

    private static final String TAG = Fragment1.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_fragment_test, Fragment1.getInstance("Daniel"), TAG)
                .commitNow();
    }
}
