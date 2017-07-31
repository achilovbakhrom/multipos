package com.jim.multipos.registration.presenters;


import com.jim.multipos.common.BaseFragmentPresenter;
import com.jim.multipos.registration.fragments.RegistrationFragmentView;

/**
 * Created by DEV on 26.07.2017.
 */

public interface RegistrationPresenter extends BaseFragmentPresenter<RegistrationFragmentView> {
    void setItems();
    void displayFragment(String name, String address, String email, String code);
    void popBackStack();
    void setRecyclerViewItems(String s, String toString);
}
