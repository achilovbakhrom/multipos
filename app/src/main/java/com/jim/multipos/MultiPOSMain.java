package com.jim.multipos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MultiPOSMain extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_posmain);
        Log.d("sss", "onCreate: " + NdkTest.getString());
        Log.d("sss", "onCreate: " + NdkTest.doAdd(5, 12));
    }


}
