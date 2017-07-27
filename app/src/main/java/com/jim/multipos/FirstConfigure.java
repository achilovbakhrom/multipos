package com.jim.multipos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jim.multipos.di.components.BaseAppComponent;

public class FirstConfigure extends BaseActivity {
    private FrameLayout leftContainer;
    private FrameLayout rightContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_configure);

        leftContainer = (FrameLayout) findViewById(R.id.leftContainer);
        rightContainer = (FrameLayout) findViewById(R.id.rightContainer);
    }

    @Override
    protected void setupComponent(BaseAppComponent baseAppComponent) {

    }
}
