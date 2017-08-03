package com.jim.multipos.ui.first_configure.fragments;

import com.jim.multipos.common.FirstConfigureFragments;

/**
 * Created by user on 01.08.17.
 */

public interface  PosDetailsFragmentView extends FirstConfigureFragments {
    public  void displayFragment(PosDetailsFragment posDetailsFragment);
    public  void popFromBackStack();
}
