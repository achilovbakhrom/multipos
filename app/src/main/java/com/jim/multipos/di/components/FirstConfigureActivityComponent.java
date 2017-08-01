package com.jim.multipos.di.components;

import com.jim.multipos.FirstConfigureActivity;
import com.jim.multipos.di.ActivityScope;
import com.jim.multipos.di.modules.FirstConfigureActivityModule;
import com.jim.multipos.first_configure.fragments.FirstConfigureLeftSideFragment;
import com.jim.multipos.first_configure.fragments.PosDetailsFragment;

import dagger.Component;

/**
 * Created by user on 31.07.17.
 */
@ActivityScope
@Component(modules = FirstConfigureActivityModule.class, dependencies = BaseAppComponent.class)
public interface FirstConfigureActivityComponent {
    void inject(FirstConfigureActivity activity);
    void inject(FirstConfigureLeftSideFragment fragment);
    void inject(PosDetailsFragment fragment);
}
