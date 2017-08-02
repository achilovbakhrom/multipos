package com.jim.multipos.ui.first_configure.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jim.multipos.R;
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.ui.first_configure.di.FirstConfigureActivityComponent;
import com.jim.multipos.ui.first_configure.adapters.SettingsAdapter;
import com.jim.multipos.ui.first_configure.FirstConfigureLeftSidePresenter;
import com.jim.multipos.utils.managers.PosFragmentManager;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstConfigureLeftSideFragment extends BaseFragment implements FirstConfigureLeftSideFragmentView {
    @Inject
    PosFragmentManager posFragmentManager;
    @Inject
    FirstConfigureLeftSidePresenter presenter;
    @Inject
    SettingsAdapter adapter;

    private RecyclerView recyclerView;

    public FirstConfigureLeftSideFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.getComponent(FirstConfigureActivityComponent.class).inject(this);

        presenter.init(this);

        View view = inflater.inflate(R.layout.start_configuration_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
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
