package com.jim.multipos.registration.presenters;

import com.jim.multipos.common.BaseFragmentPresenter;
import com.jim.multipos.registration.fragments.LoginDetailsFragmentView;

/**
 * Created by DEV on 31.07.2017.
 */

public interface LoginDetailsFragmentPresenter extends BaseFragmentPresenter<LoginDetailsFragmentView>{
    void registerFounder();
    void loginFounder();
}
