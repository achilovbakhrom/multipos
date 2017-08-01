package com.jim.multipos.ui.registration.presenters;

import com.jim.multipos.ui.registration.fragments.LoginDetailsFragmentView;

import javax.inject.Inject;

/**
 * Created by DEV on 31.07.2017.
 */

public class LoginDetailsPresenterImpl implements LoginDetailsPresenter {

    private LoginDetailsFragmentView view;

    @Inject
    public LoginDetailsPresenterImpl() {
    }

    @Override
    public void init(LoginDetailsFragmentView view) {
        this.view = view;
    }

    @Override
    public void registerFounder() {
        view.onRegistration();
    }

    @Override
    public void loginFounder() {
        view.onLogin();
    }
}
