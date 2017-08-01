package com.jim.multipos.first_configure.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jim.multipos.BaseFragment;
import com.jim.multipos.R;
import com.jim.multipos.di.components.MainActivityComponent;
import com.jim.multipos.managers.PosFragmentManager;

import javax.inject.Inject;

public class FirstConfigureRightSideFragment extends BaseFragment {
    @Inject
    PosFragmentManager posFragmentManager;

    public FirstConfigureRightSideFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(MainActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pos_details_fragment, container, false);
    }
}
