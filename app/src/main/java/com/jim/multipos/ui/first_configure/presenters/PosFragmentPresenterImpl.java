package com.jim.multipos.ui.first_configure.presenters;

import com.jim.multipos.ui.first_configure.fragments.PosDetailsFragment;
import com.jim.multipos.ui.first_configure.fragments.PosDetailsFragmentView;

/**
 * Created by user on 01.08.17.
 */

public class PosFragmentPresenterImpl implements PosFragmentPresenter {
    private PosDetailsFragmentView view;

    @Override
    public void init(PosDetailsFragmentView view) {
        this.view = view;
    }

    @Override
    public void displayFragment() {
        view.displayFragment(new PosDetailsFragment());
    }
}
