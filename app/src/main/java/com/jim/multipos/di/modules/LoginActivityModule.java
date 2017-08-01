package com.jim.multipos.di.modules;


import com.jim.multipos.views.LoginActivity;
import com.jim.multipos.di.ActivityScope;
import com.jim.multipos.managers.PosFragmentManager;
import com.jim.multipos.registration.presenters.LoginDetailsPresenter;
import com.jim.multipos.registration.presenters.LoginDetailsPresenterImpl;
import com.jim.multipos.registration.presenters.RegistrationConfirmPresenter;
import com.jim.multipos.registration.presenters.RegistrationConfirmPresenterImpl;
import com.jim.multipos.registration.presenters.RegistrationPresenter;
import com.jim.multipos.registration.presenters.RegistrationPresenterImpl;
import com.jim.multipos.views.LoginPresenter;
import com.jim.multipos.views.LoginPresenterImpl;


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
    @ActivityScope
    public LoginActivity getActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    public PosFragmentManager getFragmentManager(LoginActivity activity) {
        return new PosFragmentManager(activity);
    }

    @Provides
    @ActivityScope
    public LoginPresenter getLoginPresenter(LoginActivity loginActivity){
        return new LoginPresenterImpl(loginActivity);
    }

    @Provides
    @ActivityScope
    public RegistrationPresenter getRegistrationPresenter() {
        return new RegistrationPresenterImpl();
    }

    @Provides
    @ActivityScope
    public LoginDetailsPresenter getLoginDetailsFragmentPresenter(){
        return new LoginDetailsPresenterImpl();
    }

    @Provides
    @ActivityScope
    public RegistrationConfirmPresenter getRegistrationConfirmPresenter(){
        return new RegistrationConfirmPresenterImpl();
    }
}
