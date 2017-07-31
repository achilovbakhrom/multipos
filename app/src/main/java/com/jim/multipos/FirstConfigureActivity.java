package com.jim.multipos;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.jim.multipos.common.BaseActivity;
import com.jim.multipos.di.components.BaseAppComponent;
import com.jim.multipos.fragments.FirstConfigureLeftSideFragment;
import com.jim.multipos.fragments.FirstConfigureRightSideFragment;

public class FirstConfigureActivity extends BaseActivity {
    private FrameLayout leftContainer;
    private FrameLayout rightContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_configure);

        leftContainer = (FrameLayout) findViewById(R.id.leftContainer);
        rightContainer = (FrameLayout) findViewById(R.id.rightContainer);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.leftContainer, new FirstConfigureLeftSideFragment())
                .add(R.id.rightContainer, new FirstConfigureRightSideFragment())
                .commit();
    }

    @Override
    protected void setupComponent(BaseAppComponent baseAppComponent) {

    }
}
