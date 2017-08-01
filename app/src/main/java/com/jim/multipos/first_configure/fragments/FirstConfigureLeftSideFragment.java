package com.jim.multipos.first_configure.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;

import com.jim.multipos.BaseFragment;
import com.jim.multipos.R;
import com.jim.multipos.di.components.MainActivityComponent;
import com.jim.multipos.first_configure.adapters.SettingsAdapter;
import com.jim.multipos.first_configure.presenters.FirstConfigureLeftSideFragmentView;
import com.jim.multipos.first_configure.presenters.FirstConfigureLeftSidePresenterImpl;
import com.jim.multipos.managers.PosFragmentManager;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstConfigureLeftSideFragment extends BaseFragment implements FirstConfigureLeftSideFragmentView {
    @Inject
    PosFragmentManager posFragmentManager;
    @Inject
    FirstConfigureLeftSidePresenterImpl presenter;

    private RecyclerView recyclerView;

    public FirstConfigureLeftSideFragment() {
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
        View view = inflater.inflate(R.layout.start_configuration_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SettingsAdapter(getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
    }

    @Override
    public void displayFragment(FirstConfigureLeftSideFragment firstConfigureLeftSideFragment) {
        posFragmentManager.displayFragment(firstConfigureLeftSideFragment, R.id.leftContainer);
    }

    @Override
    public void popFromBackStack() {
        posFragmentManager.popBackStack();
    }
}
