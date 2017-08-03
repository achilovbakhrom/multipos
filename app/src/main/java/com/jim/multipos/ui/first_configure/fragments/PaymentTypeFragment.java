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
public class PaymentTypeFragment extends BaseFragment implements PaymentTypeFragmentView {
    @BindView(R.id.etPaymentTypeName)
    EditText etPaymentTypeName;
    private String paymentTypeName;

    public PaymentTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.payment_type_fragment, container, false);

        ButterKnife.bind(this, view);

        if (etPaymentTypeName != null) {
            etPaymentTypeName.setText(paymentTypeName);
        }

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        paymentTypeName = etPaymentTypeName.getText().toString();
    }
}
