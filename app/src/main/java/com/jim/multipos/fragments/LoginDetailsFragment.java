package com.jim.multipos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jim.mpviews.MpButton;
import com.jim.multipos.BaseFragment;
import com.jim.multipos.R;
import com.jim.multipos.di.components.MainActivityComponent;
import com.jim.multipos.managers.PosFragmentManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by DEV on 26.07.2017.
 */

public class LoginDetailsFragment extends BaseFragment {

    @Inject PosFragmentManager posFragmentManager;
    @BindView(R.id.btnLogin)
    MpButton btnLogin;
    @BindView(R.id.btnRegistration)
    MpButton btnRegistration;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(MainActivityComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btnLogin)
    public void login() {

    }
    @OnClick(R.id.btnRegistration)
    public void regitration() {
        posFragmentManager.displayFragment(new RegistrationFragment(), R.id.loginFragment);
    }
}
