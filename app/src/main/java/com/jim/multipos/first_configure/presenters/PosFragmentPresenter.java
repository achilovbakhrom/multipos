package com.jim.multipos.first_configure.presenters;

import com.jim.multipos.common.BaseFragmentPresenter;
import com.jim.multipos.first_configure.fragments.PosDetailsFragmentView;

/**
 * Created by user on 01.08.17.
 */

public interface PosFragmentPresenter extends BaseFragmentPresenter<PosDetailsFragmentView> {
    void displayFragment();
}
