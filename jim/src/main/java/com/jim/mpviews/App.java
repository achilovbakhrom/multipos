package com.jim.mpviews;

import android.app.Application;
import android.content.Context;

/**
 * Created by Пользователь on 15.06.2017.
 */
public class App extends Application {

    private static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
    }
}
