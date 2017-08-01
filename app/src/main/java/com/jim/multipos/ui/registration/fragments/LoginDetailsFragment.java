package com.jim.multipos.ui.registration.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jim.mpviews.MpButton;
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.R;
import com.jim.multipos.ui.registration.di.LoginActivityComponent;
import com.jim.multipos.utils.managers.PosFragmentManager;
import com.jim.multipos.ui.registration.presenters.LoginDetailsPresenter;
import com.jim.multipos.ui.registration.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by DEV on 26.07.2017.
 */

public class LoginDetailsFragment extends BaseFragment implements LoginDetailsFragmentView {

    @Inject
    PosFragmentManager posFragmentManager;
    @Inject
    LoginDetailsPresenter presenter;
    @Inject
    LoginActivity activity;
    @BindView(R.id.btnLogin)
    MpButton btnLogin;
    @BindView(R.id.btnRegistration)
    MpButton btnRegistration;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, rootView);
        this.getComponent(LoginActivityComponent.class).inject(this);
        presenter.init(this);
        return rootView;
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        presenter.loginFounder();
    }

    @OnClick(R.id.btnRegistration)
    public void registration() {
        presenter.registerFounder();
    }

    @Override
    public void onRegistration() {
        activity.openRegistration();
//        posFragmentManager.displayFragment(new RegistrationFragment(), R.id.loginFragment);
    }

    @Override
    public void onLogin() {
        //TODO open login page
    }
}
