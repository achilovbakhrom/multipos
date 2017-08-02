package com.jim.multipos.ui.first_configure.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jim.multipos.R;
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.common.FirstConfigureFragments;
import com.jim.multipos.ui.first_configure.di.FirstConfigureActivityComponent;
import com.jim.multipos.ui.first_configure.presenters.PosFragmentPresenter;
import com.jim.multipos.utils.managers.PosFragmentManager;

import java.util.HashMap;

import javax.inject.Inject;

public class PosDetailsFragment extends FirstConfigureFragments implements PosDetailsFragmentView {
    @Inject
    PosFragmentManager posFragmentManager;
    @Inject
    PosFragmentPresenter presenter;

    public PosDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pos_details_fragment, container, false);
        this.getComponent(FirstConfigureActivityComponent.class).inject(this);
        presenter.init(this);

        return view;
    }

    @Override
    public void displayFragment(PosDetailsFragment posDetailsFragment) {
        posFragmentManager.displayFragment(posDetailsFragment, R.id.rightContainer);
    }

    @Override
    public void popFromBackStack() {
        posFragmentManager.popBackStack();
    }

    @Override
    public void checkDatasComplete() {

    }

    @Override
    public HashMap<String, String> getDatas() {
        return null;
    }
}
