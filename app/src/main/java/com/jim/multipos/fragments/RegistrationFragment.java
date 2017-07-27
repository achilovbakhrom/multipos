package com.jim.multipos.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jim.mpviews.MpButton;
import com.jim.mpviews.MpEditText;
import com.jim.mpviews.MpSpinner;
import com.jim.multipos.BaseFragment;
import com.jim.multipos.R;
import com.jim.multipos.di.components.MainActivityComponent;
import com.jim.multipos.managers.PosFragmentManager;
import com.jim.multipos.presenters.RegistrationFragmentView;
import com.jim.multipos.presenters.RegistrationPresenterImpl;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DEV on 26.07.2017.
 */

public class RegistrationFragment extends BaseFragment implements RegistrationFragmentView {
    @BindView(R.id.btnBack)
    MpButton btnBack;
    @BindView(R.id.btnRegistrationSecond)
    MpButton btnRegistration;
    @BindView(R.id.etOrgName)
    MpEditText etOrgName;
    @BindView(R.id.etOrgAddress)
    MpEditText etOrgAddress;
    @BindView(R.id.etOrgEmail)
    MpEditText etOrgEmail;
    @BindView(R.id.etOrgZipCode)
    MpEditText etOrgZipCode;
    MpSpinner spContacts;
    @Inject
    RegistrationPresenterImpl presenter;
    @Inject
    PosFragmentManager posFragmentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.reg_fragment, container, false);
        ButterKnife.bind(this, rootView);
        spContacts = (MpSpinner) rootView.findViewById(R.id.spContacts);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
        presenter.setItems();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(MainActivityComponent.class).inject(this);
    }

    @OnClick(R.id.btnBack)
    public void back() {
        presenter.popBackStack();
    }

    @OnClick(R.id.btnRegistrationSecond)
    public void registration() {
      presenter.displayFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setSpinnerItems(ArrayList<String> items) {
        spContacts.setItems(items);
        spContacts.setAdapter();
    }

    @Override
    public void displayFragment() {
        posFragmentManager.displayFragment(new RegistrationConfirmFragment(), R.id.loginFragment);
    }

    @Override
    public void popFromBackStack() {
        posFragmentManager.popBackStack();
    }
}
