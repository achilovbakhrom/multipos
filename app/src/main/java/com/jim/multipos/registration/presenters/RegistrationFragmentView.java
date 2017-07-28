package com.jim.multipos.registration.presenters;

import com.jim.multipos.registration.fragments.RegistrationConfirmFragment;

import java.util.ArrayList;

/**
 * Created by DEV on 26.07.2017.
 */

public interface RegistrationFragmentView {
    void setSpinnerItems(ArrayList<String> items);
    void displayFragment(RegistrationConfirmFragment confirmFragment);
    void popFromBackStack();
}
