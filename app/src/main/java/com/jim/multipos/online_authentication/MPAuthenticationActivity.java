package com.jim.multipos.online_authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jim.multipos.R;
import com.jim.multipos.utils.CommonUtils;

import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 5/19/17.
 */

public class MPAuthenticationActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_authentication_activity);
//        displayLoginFragment();
    }

    public void displayRegisterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flAuthentication, new RegisterFragment())
                .commit();
    }

    private void displayLoginFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flAuthentication, new LoginFragment())
                .commit();
    }

}
