package com.jim.multipos.ui.menu.di;

import com.jim.multipos.ui.ActivityScope;
import com.jim.multipos.ui.menu.MainMenuActivity;

import dagger.Subcomponent;

/**
 * Created by DEV on 03.08.2017.
 */
@ActivityScope
@Subcomponent(modules = {MainMenuActivityModule.class})
public interface MainMenuActivityComponent {
    void inject(MainMenuActivity mainMenuActivity);
}
