package com.jim.multipos.di;

import com.jim.multipos.MultiPosApp;
import com.jim.multipos.entity.DaoSession;
import com.jim.multipos.utils.managers.DatabaseManager;

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
}
