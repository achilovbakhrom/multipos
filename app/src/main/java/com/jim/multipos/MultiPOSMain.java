package com.jim.multipos;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.jim.mpviews.MpSpinner;
import com.jim.mpviews.states.MpSpinnerState;
import com.jim.mpviews.utils.Test;
import com.jim.multipos.managers.DatabaseManager;
import com.jim.multipos.utils.database.DatabaseHelper;
import com.jim.multipos.utils.database.SQLiteCursor;
import com.jim.multipos.utils.database.SQLiteException;

public class MultiPOSMain extends AppCompatActivity {


    MpSpinner mpSpinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_fragment);
        mpSpinner1 = (MpSpinner) findViewById(R.id.mpSpinner1);
        mpSpinner1.setUnPickValue("default");
        mpSpinner1.setWithDefaultValueForFirstTime("(set value)");
        mpSpinner1.setItems(new String[]{"Uzs","Dollar"});
        mpSpinner1.setAdapter();
//        mpSpinner1.setState(MpSpinnerState.GROUP_CURRENCY);
        mpSpinner1.setOnItemSelectedListener(new MpSpinner.setOnItemClickListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(Test.TAG, "onItemClick: "+mpSpinner1.selectedItem());
            }
        });

    }


}
