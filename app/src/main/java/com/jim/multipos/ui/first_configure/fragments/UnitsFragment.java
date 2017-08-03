package com.jim.multipos.ui.first_configure.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jim.multipos.R;
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.common.FirstConfigureFragments;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnitsFragment extends BaseFragment {
    public UnitsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.units_settings, container, false);
    }
}
