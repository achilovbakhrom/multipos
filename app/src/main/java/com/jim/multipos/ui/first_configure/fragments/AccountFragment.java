package com.jim.multipos.ui.first_configure.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.jim.mpviews.MpButton;
import com.jim.mpviews.MpSpinner;
import com.jim.multipos.R;
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.common.FirstConfigureFragments;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends BaseFragment implements AccountFragmentView {
    @BindView(R.id.etAccountName)
    EditText etAccountName;
    @BindView(R.id.spType)
    MpSpinner spType;
    @BindView(R.id.btnNext)
    MpButton btnNext;
    @BindView(R.id.btnRevert)
    MpButton btnRevert;

    private String accountName;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.account_fragment, container, false);

        ButterKnife.bind(this, view);

        if (accountName != null) {
            etAccountName.setText(accountName);
        }

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        accountName = etAccountName.getText().toString();
    }
}
