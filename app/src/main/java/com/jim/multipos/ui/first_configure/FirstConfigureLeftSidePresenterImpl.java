package com.jim.multipos.ui.first_configure;

import com.jim.multipos.ui.first_configure.fragments.FirstConfigureLeftSideFragment;
import com.jim.multipos.ui.first_configure.fragments.FirstConfigureLeftSideFragmentView;

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
