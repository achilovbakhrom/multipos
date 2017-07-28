package com.jim.multipos.di.modules;

import android.support.v7.app.AppCompatActivity;

import com.jim.multipos.managers.PosFragmentManager;
import com.jim.multipos.registration.presenters.RegistrationPresenterImpl;


import dagger.Module;
import dagger.Provides;

/**
 * Created by DEV on 27.07.2017.
 */
@Module
public class MainActivityModule {
    private AppCompatActivity activity;

    public MainActivityModule(AppCompatActivity activity) {
       this.activity = activity;
    }

    @Provides
    public PosFragmentManager getFragmentManager(){
        return new PosFragmentManager(activity);
    }


    @Provides
    public RegistrationPresenterImpl getRegistrationPresenter(){
        return new RegistrationPresenterImpl(activity.getApplicationContext());
    }
}
