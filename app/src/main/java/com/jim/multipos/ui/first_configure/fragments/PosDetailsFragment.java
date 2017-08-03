package com.jim.multipos.ui.first_configure.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jim.mpviews.MpButton;
import com.jim.multipos.R;
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.common.FirstConfigureFragments;
import com.jim.multipos.ui.first_configure.FirstConfigureActivity;
import com.jim.multipos.ui.first_configure.di.FirstConfigureActivityComponent;
import com.jim.multipos.ui.first_configure.presenters.PosFragmentPresenter;
import com.jim.multipos.utils.managers.PosFragmentManager;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class PosDetailsFragment extends BaseFragment implements PosDetailsFragmentView {
    public static final String TAG = "myLogs";
    @Inject
    PosFragmentManager posFragmentManager;
    @Inject
    PosFragmentPresenter presenter;
    @BindView(R.id.etPosId)
    EditText etPosId;
    @BindView(R.id.etAlias)
    EditText etAlias;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etRepeatPassword)
    EditText etRepeatPassword;
    @BindView(R.id.btnNext)
    MpButton btnNext;
    @BindView(R.id.btnRevert)
    MpButton btnRevert;

    private String posID;
    private String alias;
    private String address;
    private String password;
    private String repeat;

    public PosDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pos_details_fragment, container, false);
        this.getComponent(FirstConfigureActivityComponent.class).inject(this);
        presenter.init(this);
        ButterKnife.bind(this, view);

        if (posID != null) {
            etPosId.setText(posID);
        }

        if (alias != null) {
            etAlias.setText(alias);
        }

        if (address != null) {
            etAddress.setText(address);
        }

        if (password != null) {
            etPassword.setText(password);
        }

        if (repeat != null) {
            etRepeatPassword.setText(repeat);
        }

        RxView.clicks(btnNext).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                presenter.checkData();
            }
        });

        return view;
    }

    @Override
    public void displayFragment(PosDetailsFragment posDetailsFragment) {
        posFragmentManager.displayFragment(posDetailsFragment, R.id.rightContainer);
    }

    @Override
    public void popFromBackStack() {
        posFragmentManager.popBackStack();
    }

    @Override
    public void onStop() {
        super.onStop();

        posID = etPosId.getText().toString();
        alias = etAlias.getText().toString();
        address = etAddress.getText().toString();
        password = etPassword.getText().toString();
        repeat = etRepeatPassword.getText().toString();
    }

    @Override
    public HashMap<String, String> getDatas() {
        HashMap<String, String> datas = new HashMap<>();
        String posID = etPosId.getText().toString();
        String alias = etAlias.getText().toString();
        String address = etAddress.getText().toString();
        String password = etPassword.getText().toString();
        String repeatPassword = etRepeatPassword.getText().toString();

        datas.put("posId", posID);
        datas.put("alias", alias);
        datas.put("address", address);
        datas.put("password", password);
        datas.put("repeatPassword", repeatPassword);

        return datas;
    }

    @Override
    public void showCheckedDatasResult(boolean result) {
        if (result) {
            ((FirstConfigureActivity) getActivity()).showCheckedIndicator(0, true);
        } else {
            ((FirstConfigureActivity) getActivity()).showCheckedIndicator(0, false);
            Toast.makeText(getContext(), "Data incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
