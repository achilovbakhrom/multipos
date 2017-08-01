package com.jim.multipos.ui.first_configure.di;

import com.jim.multipos.ui.first_configure.FirstConfigureActivity;
import com.jim.multipos.ui.ActivityScope;
import com.jim.multipos.ui.first_configure.FirstConfigureLeftSidePresenter;
import com.jim.multipos.ui.first_configure.FirstConfigureLeftSidePresenterImpl;
import com.jim.multipos.ui.first_configure.presenters.PosFragmentPresenter;
import com.jim.multipos.ui.first_configure.presenters.PosFragmentPresenterImpl;
import com.jim.multipos.utils.managers.PosFragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 31.07.17.
 */
@Module
public class FirstConfigureActivityModule {
    private FirstConfigureActivity activity;

    public FirstConfigureActivityModule(FirstConfigureActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public FirstConfigureActivity getActivity(){
        return activity;
    }

    @Provides
    @ActivityScope
    public PosFragmentManager getFragmentManager(FirstConfigureActivity activity) {
        return new PosFragmentManager(activity);
    }

    @Provides
    @ActivityScope
    public FirstConfigureLeftSidePresenter getFirstConfigureLeftSidePresenter() {
        return new FirstConfigureLeftSidePresenterImpl();
    }

    @Provides
    @ActivityScope
    public PosFragmentPresenter getFirstConfigureRightSidePresenter() {
        return new PosFragmentPresenterImpl();
    }
}
