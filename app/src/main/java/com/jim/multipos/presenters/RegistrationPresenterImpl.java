package com.jim.multipos.presenters;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by DEV on 26.07.2017.
 */

public class RegistrationPresenterImpl extends RegistrationPresenter {

    private RegistrationFragmentView view;

    @Inject
    public RegistrationPresenterImpl() {
    }

    @Override
    public void init(RegistrationFragmentView view) {
        this.view = view;
    }

    @Override
    public void setItems() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Phone");
        list.add("Email");
        view.setSpinnerItems(list);
    }

    @Override
    public void displayFragment() {
        view.displayFragment();
    }

    @Override
    public void popBackStack() {
        view.popFromBackStack();
    }
}
