package com.jim.multipos.presenters;

import java.util.ArrayList;

/**
 * Created by DEV on 26.07.2017.
 */

public interface RegistrationFragmentView {
    void setSpinnerItems(ArrayList<String> items);
    void displayFragment();
    void popFromBackStack();
}
