package com.jim.multipos.registration.presenters;

import com.jim.multipos.LoginActivity;
import com.jim.multipos.registration.fragments.LoginDetailsFragmentView;

import javax.inject.Inject;

/**
 * Created by DEV on 31.07.2017.
 */

public class LoginDetailsFragmentPresenterImpl implements LoginDetailsFragmentPresenter {

    private LoginDetailsFragmentView view;
    private LoginActivity activity;

    @Inject
    public LoginDetailsFragmentPresenterImpl(LoginActivity activity) {
        this.activity = activity;
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
