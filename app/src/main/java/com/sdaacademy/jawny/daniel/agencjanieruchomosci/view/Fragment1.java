package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

public class Fragment1 extends Fragment {

    private static final String NAME_KEY = Fragment1.class.getClass().getCanonicalName() + "NAME_KEY";

    public static Fragment1 getInstance(String name) {
        final Fragment1 fragment1 = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString(NAME_KEY, name);
        fragment1.setArguments(bundle);
        return fragment1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }
}
