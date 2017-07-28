package com.jim.multipos.registration.presenters;


import com.jim.multipos.BaseFragmentPresenter;

/**
 * Created by DEV on 26.07.2017.
 */

public interface RegistrationPresenter extends BaseFragmentPresenter<RegistrationFragmentView> {
    void setItems();
    void displayFragment(String name, String address, String email, String code);
    void popBackStack();
}
