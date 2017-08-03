package com.jim.multipos.ui.first_configure.presenters;

import com.jim.multipos.common.BaseFragmentPresenter;
import com.jim.multipos.ui.first_configure.fragments.PosDetailsFragmentView;

import java.util.HashMap;

/**
 * Created by user on 01.08.17.
 */

public interface PosFragmentPresenter extends BaseFragmentPresenter<PosDetailsFragmentView> {
    void displayFragment();
    void checkData();
}
