package com.jim.multipos.ui.first_configure.presenters;

import com.jim.multipos.common.BaseFragmentPresenter;
import com.jim.multipos.ui.first_configure.fragments.FirstConfigureLeftSideFragmentView;

/**
 * Created by user on 31.07.17.
 */

public interface FirstConfigureLeftSidePresenter extends BaseFragmentPresenter<FirstConfigureLeftSideFragmentView> {
    void displayFragment();
    void replaceFragment(int position);
}
