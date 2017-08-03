package com.jim.multipos.di;

import com.jim.multipos.MultiPosApp;
import com.jim.multipos.api.MultiPosApiModule;
import com.jim.multipos.entity.DaoSession;
import com.jim.multipos.ui.first_configure.di.FirstConfigureActivityComponent;
import com.jim.multipos.ui.first_configure.di.FirstConfigureActivityModule;
import com.jim.multipos.ui.menu.MainMenuActivity;
import com.jim.multipos.ui.menu.di.MainMenuActivityComponent;
import com.jim.multipos.ui.menu.di.MainMenuActivityModule;
import com.jim.multipos.ui.registration.LoginActivity;
import com.jim.multipos.ui.registration.di.LoginActivityComponent;
import com.jim.multipos.ui.registration.di.LoginActivityModule;
import com.jim.multipos.utils.managers.DatabaseManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DEV on 27.07.2017.
 */
@Singleton
@Component(modules = {BaseAppModule.class, MultiPosApiModule.class})
public interface BaseAppComponent {
   FirstConfigureActivityComponent plus(FirstConfigureActivityModule firstConfigureActivityModule);
   LoginActivityComponent plus(LoginActivityModule loginActivityModule);
   MainMenuActivityComponent plus(MainMenuActivityModule mainMenuActivityModule);
}
