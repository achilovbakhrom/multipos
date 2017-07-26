package com.jim.multipos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jim.multipos.R;

import butterknife.ButterKnife;

/**
 * Created by DEV on 26.07.2017.
 */

public class RegistrationFragment extends android.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reg_fragment, container, false);

        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
