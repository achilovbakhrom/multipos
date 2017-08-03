package com.jim.multipos.ui.first_configure.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jim.multipos.R;
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.common.FirstConfigureFragments;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployersFragment extends BaseFragment implements EmployersFragmentView {
    @BindView(R.id.etFullName)
    EditText etFullName;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirm)
    EditText etConfirm;

    private String fullName;
    private String password;
    private String confirm;

    public EmployersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.employer_settings_fragment, container, false);
        ButterKnife.bind(this, view);

        if (fullName != null) {
            etFullName.setText(fullName);
        }

        if (password != null) {
            etPassword.setText(password);
        }

        if (confirm != null) {
            etConfirm.setText(confirm);
        }

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        fullName = etFullName.getText().toString();
        password = etPassword.getText().toString();
        confirm = etConfirm.getText().toString();
    }
}
