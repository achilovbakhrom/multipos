package com.jim.multipos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jim.multipos.di.HasComponent;
import com.jim.multipos.di.components.BaseAppComponent;
import com.jim.multipos.di.components.DaggerMainActivityComponent;
import com.jim.multipos.di.components.MainActivityComponent;
import com.jim.multipos.di.modules.MainActivityModule;
import com.jim.multipos.fragments.LoginDetailsFragment;
import com.jim.multipos.managers.PosFragmentManager;

import javax.inject.Inject;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

/**
 * Created by DEV on 25.07.2017.
 */

public class LoginActivity extends BaseActivity implements HasComponent<MainActivityComponent> {
    @Inject PosFragmentManager posFragmentManager;
    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        UiChangeListener();
        posFragmentManager.displayFragment(new LoginDetailsFragment(), R.id.loginFragment);
    }

    @Override
    protected void setupComponent(BaseAppComponent baseAppComponent) {
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .baseAppComponent(baseAppComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);
    }

    public void UiChangeListener()
    {
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener (new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            }
        });
    }

    @Override
    public MainActivityComponent getComponent() {
        return mainActivityComponent;
    }
}
