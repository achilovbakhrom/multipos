package com.jim.multipos.online_authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jim.multipos.Constants;
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
        RequestQueue queue = Volley.newRequestQueue(getContext());
//        String url ="http://192.168.0.101:3000/api/v1/persons";
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Display the first 500 characters of the response string.
                        Log.d(Constants.TAG, "Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(Constants.TAG, "That didn't work!");
            }
        });
        ViewGroup viewGroup;
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
