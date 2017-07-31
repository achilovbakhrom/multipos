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
import com.jim.multipos.common.BaseFragment;
import com.jim.multipos.R;
import com.jim.multipos.di.components.LoginActivityComponent;
import com.jim.multipos.entity.ContactDao;
import com.jim.multipos.entity.DaoSession;
import com.jim.multipos.managers.DatabaseManager;
import com.jim.multipos.managers.PosFragmentManager;
import com.jim.multipos.entity.Contact;
import com.jim.multipos.registration.adapters.ContactsAdapter;
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
    private ArrayList<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.reg_fragment, container, false);
        this.getComponent(LoginActivityComponent.class).inject(this);
        ButterKnife.bind(this, rootView);
        spContacts = (MpSpinner) rootView.findViewById(R.id.spContacts);
        list = new ArrayList<>();
        presenter.init(this);
        presenter.setItems();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ;
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
    public void addContact() {
        if (!etContacts.getText().toString().equals(""))
        presenter.setRecyclerViewItems(list.get(spContacts.selectedItem()), etContacts.getText().toString());
        else etContacts.setError(getResources().getString(R.string.enter_phone_number));
    }

    @Override
    public void setSpinnerItems(ArrayList<String> items) {
        spContacts.setItems(items);
        spContacts.setAdapter();
        list = items;
    }

    @Override
    public void displayFragment(RegistrationConfirmFragment confirmFragment) {
        posFragmentManager.displayFragment(confirmFragment, R.id.loginFragment);
    }

    @Override
    public void popFromBackStack() {
        posFragmentManager.popBackStack();
    }
    @Override
    public void setRecyclerView(ArrayList<Contact> contacts) {
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
        ContactsAdapter adapter = new ContactsAdapter(contacts, getContext());
        rvContacts.setAdapter(adapter);
    }
}
