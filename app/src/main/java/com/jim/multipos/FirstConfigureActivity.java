package com.jim.multipos;

import android.os.Bundle;

import com.jim.multipos.common.BaseActivity;
import com.jim.multipos.common.FirstConfigureFragments;
import com.jim.multipos.di.HasComponent;
import com.jim.multipos.di.components.BaseAppComponent;
import com.jim.multipos.di.components.DaggerFirstConfigureActivityComponent;
import com.jim.multipos.di.components.FirstConfigureActivityComponent;
import com.jim.multipos.di.modules.FirstConfigureActivityModule;
import com.jim.multipos.first_configure.fragments.FirstConfigureLeftSideFragment;
import com.jim.multipos.first_configure.fragments.PosDetailsFragment;
import com.jim.multipos.managers.PosFragmentManager;

import java.util.ArrayList;

import javax.inject.Inject;

public class FirstConfigureActivity extends BaseActivity implements HasComponent<FirstConfigureActivityComponent>{
    /*private FrameLayout leftContainer;
    private FrameLayout rightContainer;*/
    @Inject
    PosFragmentManager posFragmentManager;
    private FirstConfigureActivityComponent firstConfigureComponent;
    ArrayList<FirstConfigureFragments> firstConfigureFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_configure);
        /*leftContainer = (Fr0 findViewById(R.id.leftContainer);
        rightContainer = (Fra0findViewById(R.id.rightContainer);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.leftContainer, new FirstConfigureLeftSideFragment())
                .add(R.id.rightContainer, new PosDetailsFragment())
                .commit();*/

        posFragmentManager.displayFragment(new FirstConfigureLeftSideFragment(), R.id.leftContainer);
        posFragmentManager.displayFragment(new PosDetailsFragment(), R.id.rightContainer);
    }

    @Override
    protected void setupComponent(BaseAppComponent baseAppComponent) {
        firstConfigureComponent = DaggerFirstConfigureActivityComponent.builder()
                .baseAppComponent(baseAppComponent)
                .firstConfigureActivityModule(new FirstConfigureActivityModule(this))
                .build();

        firstConfigureComponent.inject(this);
    }

    @Override
    public FirstConfigureActivityComponent getComponent() {
        return firstConfigureComponent;
    }
}
