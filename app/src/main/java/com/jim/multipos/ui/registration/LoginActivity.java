package com.jim.multipos.ui.registration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jim.multipos.R;
import com.jim.multipos.common.BaseActivity;
import com.jim.multipos.ui.HasComponent;
import com.jim.multipos.di.BaseAppComponent;
import com.jim.multipos.ui.registration.di.DaggerLoginActivityComponent;
import com.jim.multipos.ui.registration.di.LoginActivityComponent;
import com.jim.multipos.ui.registration.di.LoginActivityModule;
import com.jim.multipos.ui.registration.fragments.LoginDetailsFragment;
import com.jim.multipos.utils.managers.PosFragmentManager;
import com.jim.multipos.ui.registration.fragments.RegistrationConfirmFragment;
import com.jim.multipos.ui.registration.fragments.RegistrationFragment;

import javax.inject.Inject;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

/**
 * Created by DEV on 25.07.2017.
 */

public class LoginActivity extends BaseActivity implements HasComponent<LoginActivityComponent>, LoginView {
    @Inject PosFragmentManager posFragmentManager;
    @Inject LoginPresenter presenter;
    private LoginActivityComponent loginActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        UiChangeListener();
        presenter.openLoginDetails();

    }

    @Override
    protected void setupComponent(BaseAppComponent baseAppComponent) {
        loginActivityComponent = DaggerLoginActivityComponent.builder()
                .baseAppComponent(baseAppComponent)
                .loginActivityModule(new LoginActivityModule(this))
                .build();
        loginActivityComponent.inject(this);
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
    public LoginActivityComponent getComponent() {
        return loginActivityComponent;
    }

    @Override
    public void openLoginDetails() {
        posFragmentManager.displayFragment(new LoginDetailsFragment(), R.id.loginFragment);
    }

    @Override
    public void openRegistration() {
        posFragmentManager.displayFragment(new RegistrationFragment(), R.id.loginFragment);
    }

    @Override
    public void openRegistrationConfirm() {
        posFragmentManager.displayFragment(new RegistrationConfirmFragment(), R.id.loginFragment);
    }

    @Override
    public void popFromBackStack() {
        posFragmentManager.popBackStack();
    }
}
