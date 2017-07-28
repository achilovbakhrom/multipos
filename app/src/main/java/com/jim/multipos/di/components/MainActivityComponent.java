package com.jim.multipos.di.components;

import com.jim.multipos.LoginActivity;
import com.jim.multipos.di.modules.MainActivityModule;
import com.jim.multipos.registration.fragments.LoginDetailsFragment;
import com.jim.multipos.registration.fragments.RegistrationConfirmFragment;
import com.jim.multipos.registration.fragments.RegistrationFragment;



import dagger.Component;

/**
 * Created by DEV on 27.07.2017.
 */
@ActivityScope
@Component(modules = {MainActivityModule.class}, dependencies = {BaseAppComponent.class})
public interface MainActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(LoginDetailsFragment loginDetailsFragment);
    void inject(RegistrationConfirmFragment confirmFragment);
    void inject(RegistrationFragment registrationFragment);
}
