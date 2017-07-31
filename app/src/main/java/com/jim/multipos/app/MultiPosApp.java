package com.jim.multipos;

import android.app.Application;
import android.content.Context;

import com.jim.multipos.di.components.BaseAppComponent;
import com.jim.multipos.di.components().DaggerBaseAppComponent;
import com.jim.multipos.di.components.DaggerBaseAppComponent;
import com.jim.multipos.di.modules.BaseAppModule;

/**
 * Created by DEV on 27.07.2017.
 */

public class MultiPosApp extends Application {

    private BaseAppComponent appComponent;
    private Context context;

    public static MultiPosApp get(Context context) {
        return (MultiPosApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        buildComponent();
    }

    public Context getAppContext() {
        return context;
    }

    public void buildComponent() {
        appComponent = DaggerBaseAppComponent.builder()
                .baseAppModule(new BaseAppModule(this))
                .build();
        appComponent.inject(this);
    }

    public BaseAppComponent getBaseAppComponent() {
        return appComponent;
    }
}
