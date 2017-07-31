package com.jim.multipos.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jim.multipos.app.MultiPosApp;
import com.jim.multipos.di.components.BaseAppComponent;

/**
 * Created by user on 26.07.17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(MultiPosApp.get(this).getBaseAppComponent());
        hideStatusBar();
    }

    public void hideStatusBar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    protected abstract void setupComponent(BaseAppComponent baseAppComponent);

}
