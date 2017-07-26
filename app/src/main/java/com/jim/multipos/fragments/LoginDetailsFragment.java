package com.jim.multipos.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.jim.mpviews.MpButton;
import com.jim.multipos.R;
import com.jim.multipos.managers.PosFragmentManager;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by DEV on 26.07.2017.
 */

public class LoginDetailsFragment extends Fragment {

    @BindView(R.id.btnLogin)
    MpButton btnLogin;
    @BindView(R.id.btnRegistration)
    MpButton btnRegistration;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_fragment, container, false);

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btnLogin)
    public void login() {

    }
    @OnClick(R.id.btnRegistration)
    public void registr() {
        PosFragmentManager posFragmentManager = new PosFragmentManager(getActivity());
        posFragmentManager.displayFragment(new RegistrationFragment(), R.id.loginFragment);
    }
}
