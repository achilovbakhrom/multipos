package com.jim.multipos.registration.presenters;


import com.jim.multipos.registration.fragments.RegistrationConfirmFragmentView;

/**
 * Created by DEV on 31.07.2017.
 */

public class RegistrationConfirmPresenterImpl implements RegistrationConfirmPresenter {
    private RegistrationConfirmFragmentView view;

    public RegistrationConfirmPresenterImpl() {
            }

    @Override
    public void init(RegistrationConfirmFragmentView view) {
        this.view = view;
    }

    @Override
    public void confirm() {
    }

    @Override
    public void checkAccessToken() {

    }

    @Override
    public void back() {
        view.onBack();
    }
}
