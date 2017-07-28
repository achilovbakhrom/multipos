package com.jim.multipos.registration.presenters;

import android.content.Context;
import android.os.Bundle;

import com.jim.multipos.R;
import com.jim.multipos.registration.fragments.RegistrationConfirmFragment;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import static com.jim.multipos.utils.Constants.ORG_ADDRESS;
import static com.jim.multipos.utils.Constants.ORG_CODE;
import static com.jim.multipos.utils.Constants.ORG_EMAIL;
import static com.jim.multipos.utils.Constants.ORG_NAME;

/**
 * Created by DEV on 26.07.2017.
 */

public class RegistrationPresenterImpl implements RegistrationPresenter {
    private Context context;
    private RegistrationFragmentView view;
    private String[] contacts;

    @Inject
    public RegistrationPresenterImpl(Context context) {
        this.context = context;
         contacts = new String[]{context.getString(R.string.phone), context.getString(R.string.email)};
    }

    @Override
    public void init(RegistrationFragmentView view) {
        this.view = view;
    }

    @Override
    public void setItems() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(contacts));
        view.setSpinnerItems(list);
    }

    @Override
    public void displayFragment(String name, String address, String email, String code) {
        Bundle bundle = new Bundle();
        bundle.putString(ORG_NAME, name);
        bundle.putString(ORG_ADDRESS, address);
        bundle.putString(ORG_EMAIL, email);
        bundle.putString(ORG_CODE, code);
        RegistrationConfirmFragment confirmFragment = new RegistrationConfirmFragment();
        confirmFragment.setArguments(bundle);
        view.displayFragment(confirmFragment);
    }

    @Override
    public void popBackStack() {
        view.popFromBackStack();
    }
}
