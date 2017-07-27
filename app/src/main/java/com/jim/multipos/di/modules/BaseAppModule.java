package com.jim.multipos.di.modules;

import android.app.Application;

import com.jim.multipos.MultiPosApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DEV on 27.07.2017.
 */
@Module
public class BaseAppModule {
    private final MultiPosApp app;

    public BaseAppModule(MultiPosApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }
}
