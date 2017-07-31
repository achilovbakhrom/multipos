package com.jim.multipos.di.components;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.jim.multipos.app.MultiPosApp;
import com.jim.multipos.di.modules.BaseAppModule;
import com.jim.multipos.entity.DaoSession;
import com.jim.multipos.managers.DatabaseManager;
import com.jim.multipos.registration.adapters.ContactsAdapter;
import com.jim.multipos.registration.presenters.RegistrationPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DEV on 27.07.2017.
 */
@Singleton
@Component(modules = {BaseAppModule.class})
public interface BaseAppComponent {
    DaoSession getDaoSession();
    void inject(MultiPosApp multiPosApp);
    void inject(DatabaseManager databaseManager);
    void inject(RegistrationPresenterImpl registrationPresenter);
    void inject(ContactsAdapter contactsAdapter);
}
