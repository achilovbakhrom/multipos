package com.jim.multipos.first_configure.presenters;

import com.jim.multipos.first_configure.fragments.FirstConfigureLeftSideFragment;

/**
 * Created by user on 31.07.17.
 */

public class FirstConfigureLeftSidePresenterImpl implements FirstConfigureLeftSidePresenter {
    private FirstConfigureLeftSideFragmentView view;

    @Override
    public void init(FirstConfigureLeftSideFragmentView view) {
        this.view = view;
    }

    @Override
    public void displayFragment() {
        view.displayFragment(new FirstConfigureLeftSideFragment());
    }
}
