package com.jim.multipos.di.modules;


import com.jim.multipos.LoginActivity;
import com.jim.multipos.managers.PosFragmentManager;
import com.jim.multipos.registration.presenters.LoginDetailsPresenterImpl;
import com.jim.multipos.registration.presenters.RegistrationConfirmPresenterImpl;
import com.jim.multipos.registration.presenters.RegistrationPresenterImpl;


import dagger.Module;
import dagger.Provides;

/**
 * Created by DEV on 27.07.2017.
 */
@Module
public class LoginActivityModule {
    private LoginActivity activity;

    public LoginActivityModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    public LoginActivity getActivity() {
        return activity;
    }

    @Provides
    public PosFragmentManager getFragmentManager(LoginActivity activity) {
        return new PosFragmentManager(activity);
    }


    @Provides
    public RegistrationPresenterImpl getRegistrationPresenter(LoginActivity activity) {
        return new RegistrationPresenterImpl(activity);
    }

    @Provides
    public LoginDetailsPresenterImpl getLoginDetailsFragmentPresenter(LoginActivity activity){
        return new LoginDetailsPresenterImpl(activity);
    }

    @Provides
    public RegistrationConfirmPresenterImpl getRegistrationConfirmPresenter(LoginActivity activity){
        return new RegistrationConfirmPresenterImpl(activity);
    }
}
