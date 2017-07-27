package com.jim.multipos.presenters;


import com.jim.multipos.BaseFragmentPresenter;

/**
 * Created by DEV on 26.07.2017.
 */

public abstract class RegistrationPresenter implements BaseFragmentPresenter<RegistrationFragmentView> {
    public abstract void setItems();
    public abstract void displayFragment();
    public abstract void popBackStack();
}
