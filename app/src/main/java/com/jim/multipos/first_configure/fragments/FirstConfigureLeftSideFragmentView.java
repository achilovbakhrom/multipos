package com.jim.multipos.first_configure.presenters;

import com.jim.multipos.first_configure.fragments.FirstConfigureLeftSideFragment;

/**
 * Created by user on 31.07.17.
 */

public interface FirstConfigureLeftSideFragmentView {
    void displayFragment(FirstConfigureLeftSideFragment firstConfigureLeftSideFragment);
    void popFromBackStack();
}