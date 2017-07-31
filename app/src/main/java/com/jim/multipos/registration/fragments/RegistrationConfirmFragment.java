package com.jim.multipos.registration.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jim.mpviews.MpButton;
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.R;
import com.jim.multipos.di.components.LoginActivityComponent;
import com.jim.multipos.managers.PosFragmentManager;
import com.jim.multipos.registration.presenters.RegistrationConfirmPresenterImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DEV on 26.07.2017.
 */

public class RegistrationConfirmFragment extends BaseFragment implements RegistrationConfirmFragmentView {
    @Inject
    PosFragmentManager posFragmentManager;
    @Inject
    RegistrationConfirmPresenterImpl presenter;
    @BindView(R.id.btnBack)
    MpButton btnBack;
    @BindView(R.id.btnConfirm)
    MpButton btnConfirm;
    @BindView(R.id.ivEditDetails)
    ImageView ivEditDetails;
    @BindView(R.id.tvOrgName)
    TextView tvOrgName;
    @BindView(R.id.tvOrgEmail)
    TextView tvOrgEmail;
    @BindView(R.id.tvOrgAddress)
    TextView tvOrgAddress;
    @BindView(R.id.tvOrgZipCode)
    TextView tvOrgZipCode;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reg_second_page_fragment, container, false);
        ButterKnife.bind(this, rootView);
        this.getComponent(LoginActivityComponent.class).inject(this);
        presenter.init(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @OnClick(R.id.btnBack)
    public void back() {
        presenter.back();

    }

    @OnClick(R.id.btnConfirm)
    public void confirm() {
        presenter.confirm();
    }

    @OnClick(R.id.ivEditDetails)
    public void edit() {
        presenter.back();
    }

    @Override
    public void onConfirm() {

    }

    @Override
    public void checkToken() {

    }

    @Override
    public void onBackPressed() {
        posFragmentManager.popBackStack();
    }
}
