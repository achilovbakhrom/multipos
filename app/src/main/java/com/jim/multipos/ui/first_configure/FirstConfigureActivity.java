package com.jim.multipos.ui.first_configure;

import android.os.Bundle;

import com.jim.multipos.R;
import com.jim.multipos.common.BaseActivity;
import com.jim.multipos.common.FirstConfigureFragments;
import com.jim.multipos.ui.HasComponent;
import com.jim.multipos.di.BaseAppComponent;
import com.jim.multipos.ui.first_configure.di.FirstConfigureActivityModule;
import com.jim.multipos.ui.first_configure.di.FirstConfigureActivityComponent;
import com.jim.multipos.ui.first_configure.fragments.AccountFragment;
import com.jim.multipos.ui.first_configure.fragments.CurrencyFragment;
import com.jim.multipos.ui.first_configure.fragments.EmployersFragment;
import com.jim.multipos.ui.first_configure.fragments.FirstConfigureLeftSideFragment;
import com.jim.multipos.ui.first_configure.fragments.PaymentTypeFragment;
import com.jim.multipos.ui.first_configure.fragments.PosDetailsFragment;
import com.jim.multipos.ui.first_configure.fragments.UnitsFragment;
import com.jim.multipos.utils.managers.PosFragmentManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FirstConfigureActivity extends BaseActivity implements HasComponent<FirstConfigureActivityComponent>, FirstConfigureView {
    @Inject
    PosFragmentManager posFragmentManager;
    private FirstConfigureActivityComponent firstConfigureComponent;
    List<FirstConfigureFragments> firstConfigureFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_configure);

        firstConfigureFragments = new ArrayList<>();
        firstConfigureFragments.add(new PosDetailsFragment());
        firstConfigureFragments.add(new AccountFragment());
        firstConfigureFragments.add(new PaymentTypeFragment());
        firstConfigureFragments.add(new CurrencyFragment());
        firstConfigureFragments.add(new UnitsFragment());
        firstConfigureFragments.add(new EmployersFragment());

        posFragmentManager.replaceFragment(new FirstConfigureLeftSideFragment(), R.id.leftContainer);
        posFragmentManager.replaceFragment(firstConfigureFragments.get(0), R.id.rightContainer);
    }

    @Override
    protected void setupComponent(BaseAppComponent baseAppComponent) {
        firstConfigureComponent = baseAppComponent.plus(new FirstConfigureActivityModule(this));
        firstConfigureComponent.inject(this);
    }

    @Override
    public FirstConfigureActivityComponent getComponent() {
        return firstConfigureComponent;
    }

    @Override
    public void replaceFragment(int position) {
        posFragmentManager.replaceFragment(firstConfigureFragments.get(position), R.id.rightContainer);
    }
}
