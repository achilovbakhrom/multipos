package com.jim.multipos.registration.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jim.mpviews.MpButton;
import com.jim.mpviews.MpEditText;
import com.jim.mpviews.MpSpinner;
import com.jim.multipos.BaseFragment;
import com.jim.multipos.R;
import com.jim.multipos.di.components.MainActivityComponent;
import com.jim.multipos.managers.PosFragmentManager;
import com.jim.multipos.registration.presenters.RegistrationFragmentView;
import com.jim.multipos.registration.presenters.RegistrationPresenterImpl;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DEV on 26.07.2017.
 */

public class RegistrationFragment extends BaseFragment implements RegistrationFragmentView {
    @BindView(R.id.btnBack)
    MpButton btnBack;
    @BindView(R.id.btnRegistrationSecond)
    MpButton btnRegistration;
    @BindView(R.id.etOrgName)
    MpEditText etOrgName;
    @BindView(R.id.etOrgAddress)
    MpEditText etOrgAddress;
    @BindView(R.id.etOrgEmail)
    MpEditText etOrgEmail;
    @BindView(R.id.etOrgZipCode)
    MpEditText etOrgZipCode;
    @BindView(R.id.etContacts)
    MpEditText etContacts;
    @BindView(R.id.rvContacts)
    RecyclerView rvContacts;
    @BindView(R.id.ivAddContact)
    ImageView ivAddContact;
    MpSpinner spContacts;
    @Inject
    RegistrationPresenterImpl presenter;
    @Inject
    PosFragmentManager posFragmentManager;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> contacts;
    ArrayList<String> contactType;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.reg_fragment, container, false);
        ButterKnife.bind(this, rootView);
        spContacts = (MpSpinner) rootView.findViewById(R.id.spContacts);
        contacts = new ArrayList<>();
        contactType = new ArrayList<>();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
        presenter.setItems();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(MainActivityComponent.class).inject(this);
    }

    @OnClick(R.id.btnBack)
    public void back() {
        presenter.popBackStack();
    }

    @OnClick(R.id.btnRegistrationSecond)
    public void registration() {

        String code = etOrgZipCode.getText().toString();
        String address = etOrgAddress.getText().toString();
        String name = etOrgName.getText().toString();
        String email = etOrgEmail.getText().toString();

        presenter.displayFragment(name, address, email, code);
    }

    @OnClick(R.id.ivAddContact)
    public void addContact(){
//        presenter.addContact();
    }

    @Override
    public void setSpinnerItems(ArrayList<String> items) {
        spContacts.setItems(items);
        spContacts.setAdapter();
    }

    @Override
    public void displayFragment(RegistrationConfirmFragment confirmFragment) {
        posFragmentManager.displayFragment(confirmFragment, R.id.loginFragment);
    }

    @Override
    public void popFromBackStack() {
        posFragmentManager.popBackStack();
    }

    private void setRecyclerView(){

        layoutManager = new LinearLayoutManager(getContext());
        rvContacts.setLayoutManager(layoutManager);
//        contacts.add(etContacts.getText().toString());
//        contactType.add(items.get(spContacts.selectedItem()));
//        ContactsAdapter adapter = new ContactsAdapter(contactType, contacts);
//        rvContacts.setAdapter(adapter);
    }
}
