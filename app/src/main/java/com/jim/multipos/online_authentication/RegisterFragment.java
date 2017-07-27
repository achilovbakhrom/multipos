package com.jim.multipos.online_authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jim.multipos.R;

/**
 * Created by Developer on 5/19/17.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener{
    private EditText etLogin, etPassword, etEmail;
    private Button btnRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mp_register, container, false);

        etLogin = (EditText) root.findViewById(R.id.etLogin);
        etPassword = (EditText) root.findViewById(R.id.etPassword);
        etEmail = (EditText) root.findViewById(R.id.etEmail);
        btnRegister = (Button) root.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);



        return root;
    }


    @Override
    public void onClick(View view) {

    }
}
