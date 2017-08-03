package com.jim.multipos.ui.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jim.multipos.R;
import com.jim.multipos.common.BaseActivity;
import com.jim.multipos.di.BaseAppComponent;
import com.jim.multipos.ui.HasComponent;
import com.jim.multipos.ui.menu.di.MainMenuActivityComponent;
import com.jim.multipos.ui.menu.di.MainMenuActivityModule;

/**
 * Created by DEV on 03.08.2017.
 */

public class MainMenuActivity extends BaseActivity implements HasComponent<MainMenuActivityComponent> {
    private MainMenuActivityComponent mainMenuActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
    }

    @Override
    protected void setupComponent(BaseAppComponent baseAppComponent) {
        mainMenuActivityComponent = baseAppComponent.plus(new MainMenuActivityModule(this));
        mainMenuActivityComponent.inject(this);
    }

    @Override
    public MainMenuActivityComponent getComponent() {
        return mainMenuActivityComponent;
    }
}
