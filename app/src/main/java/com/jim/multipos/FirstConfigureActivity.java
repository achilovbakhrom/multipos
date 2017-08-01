package com.jim.multipos;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.jim.multipos.di.HasComponent;
import com.jim.multipos.di.components.BaseAppComponent;
import com.jim.multipos.di.components.MainActivityComponent;
import com.jim.multipos.di.modules.MainActivityModule;
import com.jim.multipos.first_configure.fragments.FirstConfigureLeftSideFragment;
import com.jim.multipos.first_configure.fragments.FirstConfigureRightSideFragment;
import com.jim.multipos.managers.PosFragmentManager;
import com.jim.multipos.registration.fragments.LoginDetailsFragment;

import javax.inject.Inject;

public class FirstConfigureActivity extends BaseActivity implements HasComponent<MainActivityComponent>{
    /*private FrameLayout leftContainer;
    private FrameLayout rightContainer;*/
    @Inject
    PosFragmentManager posFragmentManager;
    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_configure);

        /*leftContainer = (FrameLayout) findViewById(R.id.leftContainer);
        rightContainer = (FrameLayout) findViewById(R.id.rightContainer);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.leftContainer, new FirstConfigureLeftSideFragment())
                .add(R.id.rightContainer, new FirstConfigureRightSideFragment())
                .commit();*/

        posFragmentManager.displayFragment(new FirstConfigureLeftSideFragment(), R.id.leftContainer);
    }

    @Override
    protected void setupComponent(BaseAppComponent baseAppComponent) {
        mainActivityComponent = DaggerMainAcitivityComponent.builder()
                .baseAppComponent(baseAppComponent)
                .mainAcitivtyModule(new MainActivityModule(this))
                .build();

        mainActivityComponent.inject(this);
    }

    @Override
    public MainActivityComponent getComponent() {
        return mainActivityComponent;
    }
}
