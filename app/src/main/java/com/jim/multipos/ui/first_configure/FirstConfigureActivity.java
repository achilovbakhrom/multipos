package com.jim.multipos.ui.first_configure;

import android.os.Bundle;

import com.jim.multipos.R;
import com.jim.multipos.common.BaseActivity;
import com.jim.multipos.common.BaseFragment;
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
    List<BaseFragment> firstConfigureFragments;
    private FirstConfigureLeftSideFragment leftSideFragment;
    private int currentFragmentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_configure);

        firstConfigureFragments = new ArrayList<>();
        firstConfigureFragments.add(new PosDetailsFragment());
        /*firstConfigureFragments.add(new AccountFragment());
        firstConfigureFragments.add(new PaymentTypeFragment());
        firstConfigureFragments.add(new CurrencyFragment());
        firstConfigureFragments.add(new UnitsFragment());
        firstConfigureFragments.add(new EmployersFragment());*/

        leftSideFragment = new FirstConfigureLeftSideFragment();
        posFragmentManager.replaceFragment(leftSideFragment, R.id.leftContainer);
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

    public void replaceFragment(int position) {
        currentFragmentPosition = position;
        posFragmentManager.replaceFragment(firstConfigureFragments.get(position), R.id.rightContainer);
    }

    public void showCheckedIndicator(int position, boolean checked) {
        leftSideFragment.showCheckBoxIndicator(position, checked);
    }

    public void openNextFragment() {
        replaceFragment(currentFragmentPosition + 1);
    }
}
