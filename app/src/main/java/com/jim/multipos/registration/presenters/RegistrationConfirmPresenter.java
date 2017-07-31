package com.jim.multipos.registration.presenters;

import com.jim.multipos.common.BaseFragmentPresenter;
import com.jim.multipos.registration.fragments.RegistrationConfirmFragmentView;

/**
 * Created by DEV on 31.07.2017.
 */

public interface RegistrationConfirmPresenter extends BaseFragmentPresenter<RegistrationConfirmFragmentView> {
    void confirm();
    void checkAccessToken();
    void back();
}
