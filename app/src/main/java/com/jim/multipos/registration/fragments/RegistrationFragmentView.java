package com.jim.multipos.registration.fragments;

import com.jim.multipos.entity.Contact;

import java.util.ArrayList;

/**
 * Created by DEV on 26.07.2017.
 */

public interface RegistrationFragmentView {
    void setSpinnerItems(ArrayList<String> items);
    void displayFragment(RegistrationConfirmFragment confirmFragment);
    void popFromBackStack();
    void setRecyclerView(ArrayList<Contact> contacts);
}
