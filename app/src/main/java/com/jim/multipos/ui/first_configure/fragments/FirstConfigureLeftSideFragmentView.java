package com.jim.multipos.ui.first_configure.fragments;

/**
 * Created by user on 31.07.17.
 */

public interface FirstConfigureLeftSideFragmentView {
    void displayFragment(FirstConfigureLeftSideFragment firstConfigureLeftSideFragment);
    void replaceFragment(int position);
    void popFromBackStack();
}