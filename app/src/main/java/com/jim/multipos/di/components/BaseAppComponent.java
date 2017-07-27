package com.jim.multipos.di.components;

import com.jim.multipos.MultiPosApp;
import com.jim.multipos.di.modules.BaseAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DEV on 27.07.2017.
 */
@Singleton
@Component(modules = {BaseAppModule.class})
public interface BaseAppComponent {
    void inject(MultiPosApp multiPosApp);
}
