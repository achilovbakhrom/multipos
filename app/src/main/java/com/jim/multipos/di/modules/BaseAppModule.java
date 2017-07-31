package com.jim.multipos.di.modules;

import android.app.Application;
import android.content.Context;

import com.jim.multipos.LoginActivity;
import com.jim.multipos.app.MultiPosApp;
import com.jim.multipos.entity.DaoMaster;
import com.jim.multipos.entity.DaoSession;
import com.jim.multipos.managers.DatabaseManager;

import org.greenrobot.greendao.database.Database;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DEV on 27.07.2017.
 */
@Module
public class BaseAppModule {
    private DaoSession daoSession;

    private final MultiPosApp app;

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    public BaseAppModule(MultiPosApp app) {
        this.app = app;
    }

    @Provides
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app.getApplicationContext(), "currency-db");
            Database db = helper.getWritableDb();
            daoSession = new DaoMaster(db).newSession();
        }
        return daoSession;
    }

    @Provides
    @Singleton
    public DatabaseManager getDatabaseManager() {
        return new DatabaseManager(app.getApplicationContext());
    }

}
