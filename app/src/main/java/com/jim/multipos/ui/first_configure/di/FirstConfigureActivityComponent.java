package com.jim.multipos.ui.first_configure.di;

import com.jim.multipos.di.BaseAppComponent;
import com.jim.multipos.ui.first_configure.FirstConfigureActivity;
import com.jim.multipos.ui.ActivityScope;
import com.jim.multipos.ui.first_configure.fragments.FirstConfigureLeftSideFragment;
import com.jim.multipos.ui.first_configure.fragments.PosDetailsFragment;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by user on 31.07.17.
 */
@ActivityScope
@Subcomponent(modules = FirstConfigureActivityModule.class)
public interface FirstConfigureActivityComponent {
    void inject(FirstConfigureActivity activity);
    void inject(FirstConfigureLeftSideFragment fragment);
    void inject(PosDetailsFragment fragment);
}
