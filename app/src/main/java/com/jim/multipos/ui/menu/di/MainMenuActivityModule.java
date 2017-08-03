package com.jim.multipos.ui.menu.di;

import com.jim.multipos.ui.ActivityScope;
import com.jim.multipos.ui.menu.MainMenuActivity;
import com.jim.multipos.utils.managers.PosFragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DEV on 03.08.2017.
 */
@Module
public class MainMenuActivityModule {
    private MainMenuActivity activity;

    public MainMenuActivityModule(MainMenuActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public MainMenuActivity getActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    public PosFragmentManager getFragmentManager(MainMenuActivity activity) {
        return new PosFragmentManager(activity);
    }


}
